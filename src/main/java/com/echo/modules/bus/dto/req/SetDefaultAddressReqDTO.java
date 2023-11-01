package com.echo.modules.bus.dto.req;

import com.echo.modules.bus.model.BusAddressBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetDefaultAddressReqDTO extends BusAddressBook {
}
