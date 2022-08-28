package com.lightbox.producer.rest;



import com.lightbox.dto.LightboxRequestDTO;
import com.lightbox.services.LightboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import static com.lightbox.rest.base.BaseController.API_BASE;
import static com.lightbox.rest.base.BaseController.ACCEPT;


@RestController
@RequestMapping(API_BASE + ACCEPT)
public class LightboxController {

    final protected Logger logger = LoggerFactory.getLogger(getClass());
    private LightboxService lightboxService;

    @Autowired
    public LightboxController(LightboxService lightboxService) {
        this.lightboxService = lightboxService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    @CrossOrigin(origins = "*")
    public String accept(@RequestBody(required = true) LightboxRequestDTO request) {
        logger.info("Received the message  [{}] for lightbox queue", request.getMsg());
         lightboxService.addToLightBoxQueue(request);
         return "Message added to lightbox";
    }


}
