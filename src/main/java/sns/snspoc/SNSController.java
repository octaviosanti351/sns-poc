package sns.snspoc;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {
    @Autowired
    private AmazonSNSClient amazonSNSClient;

    // URL - http://localhost:10093/addSubscription/myemail@somecompany.com
    // NOTE - In this tutorial, we are skipping the email validation part. Trusting that you'll add a valid email address.
    @PostMapping(value = "/addSubscription/{email}")
    public ResponseEntity<String> addSubscription(@PathVariable final String email) {
        final SubscribeRequest subscribeRequest = new SubscribeRequest("arn:aws:sns:us-east-2:039861428336:prueba-octa", "email", email);
        amazonSNSClient.subscribe(subscribeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // URL - http://localhost:10093/sendNotification
    // Sample request body -

    @PostMapping(value = "/sendNotification")
    public ResponseEntity<String> publishMessageToTopic(@RequestBody final SnsMail notification) {
        final PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-east-2:039861428336:prueba-octa", notification.getMessage(), notification.getSubject());
        amazonSNSClient.publish(publishRequest);
        return new ResponseEntity<>("Notification sent successfully!!", HttpStatus.OK);
    }
}
