package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieByYear {
    private int startYear;
    private int endYear;
}
