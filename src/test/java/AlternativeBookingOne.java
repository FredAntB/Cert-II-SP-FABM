import lombok.Getter;
import lombok.Setter;

public class AlternativeBookingOne {
    @Getter
    @Setter
    private String firstname;
    @Getter @Setter
    private Integer lastname;
    @Getter @Setter
    private Integer totalprice;
    @Getter @Setter
    private Boolean depositpaid;
    @Getter @Setter
    private Bookingdates bookingdates;
    @Getter @Setter
    private String additionalneeds;
}
