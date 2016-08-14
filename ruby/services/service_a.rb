class ServiceA
	def self.list
		time_to_sleep = Random.rand(0..10)
		sleep time_to_sleep
		time_to_sleep
	end
end