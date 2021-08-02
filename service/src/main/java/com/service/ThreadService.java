package com.service;

import org.springframework.stereotype.Service;

@Service
public interface ThreadService {

    void threadsError();
    void threadsOk();
    void threadsOkError();
}
