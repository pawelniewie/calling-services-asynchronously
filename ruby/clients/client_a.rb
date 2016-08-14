class ClientA
  include Dry::Monads::Either::Mixin

  def data
    Right ServiceA.list
  rescue StandardError => e
    Left e
  end
end