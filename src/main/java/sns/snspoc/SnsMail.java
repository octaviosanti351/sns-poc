package sns.snspoc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class SnsMail {

    private String subject;
    private String message;
}
