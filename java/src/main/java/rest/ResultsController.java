package rest;

import aggregate.ConnectionsService;
import aggregate.ResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ResultsController {
    @Autowired
    ConnectionsService connectionsService;

    @RequestMapping(method = GET)
    public ResultsDto getConnections() throws InterruptedException {
        return connectionsService.getConnections();
    }
}
