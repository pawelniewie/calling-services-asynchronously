class ClientB
  include Dry::Monads::Either::Mixin

  def data
    Right ServiceB.process
  rescue StandardError => e
    Left e
  end
end