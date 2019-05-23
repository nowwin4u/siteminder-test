package com.siteminder.abhijit.test.emailservice;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailMessage {

  @NotNull
  private String fullName;

  @NotNull
  @Email
  private String email;

  @NotNull
  @Min(2)
  private String message;
}
