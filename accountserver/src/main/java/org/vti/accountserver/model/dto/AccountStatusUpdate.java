package org.vti.accountserver.model.dto;

import lombok.Data;
import org.vti.accountserver.model.AccountStatus;

@Data
public class AccountStatusUpdate {
    AccountStatus accountStatus;
}
