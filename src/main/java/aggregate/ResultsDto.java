package aggregate;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ResultsDto {
    List<String> results;
}
