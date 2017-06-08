package com.aldebaran.auth.service

import com.aldebaran.auth.core.storage.SecurityStorageProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
open class SecurityServiceImpl
    @Autowired constructor(storageProxy: SecurityStorageProxy): SecurityService {

    override fun revoke(jwt: String) {

    }
}