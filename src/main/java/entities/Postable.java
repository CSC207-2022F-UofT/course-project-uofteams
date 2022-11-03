package java.entities;

import java.util.List;

abstract class Postable{
    User user;
    String body;
    List<Postable> replies;
}

