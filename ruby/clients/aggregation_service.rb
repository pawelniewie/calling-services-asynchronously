require 'concurrent'

class AggregationService
  MAX_WAIT_TIME = 5

  include Dry::Monads::Either::Mixin

  def initialize(clients = nil)
    @clients = clients || [ClientA.new, ClientB.new]
    @pool = Concurrent::ThreadPoolExecutor.new({
      min_threads: @clients.size,
      max_threads: Concurrent.processor_count
    })
  end

  def results
    lock = Concurrent::CountDownLatch.new(@clients.size)

    futures = @clients.map do |client|
      Concurrent::Future.execute(executor: @pool) do
        begin
          client.data()
        ensure
          lock.count_down
        end
      end
    end

    lock.wait(MAX_WAIT_TIME)

    results = futures.select { |f| f.fulfilled? }.map(&:value)

    {
      results: results.select { |either| either.success? }.map { |r| r.right },
      errors: results.select { |either| either.failure? }.map { |l| l.left }
    }
  end
end
