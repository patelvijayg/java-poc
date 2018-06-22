package com.javasampleapproach.lecturerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BeanPropertyInfo {
    @Value("${testing.information}")
    private String information;

    @Override
    public String toString() {
        return this.information;
    }
}
