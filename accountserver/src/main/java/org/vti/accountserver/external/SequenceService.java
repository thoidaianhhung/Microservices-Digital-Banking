package org.vti.accountserver.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.vti.accountserver.model.dto.external.SequenceDto;

@FeignClient(name = "sequence-generator")
public interface SequenceService {

    /**
     * Generates a new account number.
     *
     * @return the generated account number as a SequenceDto object.
     */
    @PostMapping("/sequence")
    SequenceDto generateAccountNumber();
}
