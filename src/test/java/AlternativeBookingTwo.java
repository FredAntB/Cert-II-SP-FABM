import lombok.Getter;
import lombok.Setter;

public class AlternativeBookingTwo {
    @Getter
    @Setter
    private String firstname;
    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private String totalprice;
    @Getter @Setter
    private Boolean depositpaid;
    @Getter @Setter
    private Bookingdates bookingdates;
    @Getter @Setter
    private String additionalneeds;
}
