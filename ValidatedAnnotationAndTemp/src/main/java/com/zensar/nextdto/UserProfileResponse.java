package com.zensar.nextdto;

import java.util.List;

public record UserProfileResponse(
    User user,
    List<Order> orders,
    List<Payment> payments
) {}
