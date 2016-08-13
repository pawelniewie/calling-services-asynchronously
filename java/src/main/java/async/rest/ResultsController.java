package async.rest;

import async.aggregate.AggregationService;
import async.aggregate.ResultsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ResultsController {
    @Autowired
    AggregationService aggregationService;

    @RequestMapping(method = GET)
    public ResultsDto getResults() throws InterruptedException {
        return aggregationService.getResults();
    }
}
