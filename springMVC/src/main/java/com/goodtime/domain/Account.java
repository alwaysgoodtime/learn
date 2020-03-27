package com.goodtime.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author goodtime
 * @create 2020-03-21 8:38 下午
 */
@Data
public class Account implements Serializable {
  private String name;
  private String password;
  private User user;
}
