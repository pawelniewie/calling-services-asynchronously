require 'rubygems'
require 'bundler'

Bundler.require

require 'sinatra'

Dir[File.dirname(__FILE__) + '/services/*.rb'].each { |file| require file }
Dir[File.dirname(__FILE__) + '/clients/*.rb'].each { |file| require file }

configure {
  set :server, :puma
}

get '/' do
  content_type :json

  AggregationService.new.results.to_json
end
