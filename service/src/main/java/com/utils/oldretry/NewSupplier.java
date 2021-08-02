package com.utils.oldretry;

/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */




@FunctionalInterface
public interface NewSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    void get() throws Exception;
}
