package com.echo.modules.bus.dto.req;

import com.echo.modules.bus.model.BusAddressBook;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressBookRequestDTO extends BusAddressBook {



}
