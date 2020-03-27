package com.goodtime.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author goodtime
 * @create 2020-03-21 8:38 下午
 */
@Data
public class Member implements Serializable {
  private String name;
  private String password;
  private List<User> userlist;
  private Map<String,User> usermap;
}
