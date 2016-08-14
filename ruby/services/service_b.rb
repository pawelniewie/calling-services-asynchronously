class ServiceB
	def self.process
		time_to_sleep = Random.rand(0..10)
		sleep time_to_sleep
		time_to_sleep
	end
end