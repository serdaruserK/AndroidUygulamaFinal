package com.example.newspapers

class User {
    var pass: Int = 0
    var mail: String? = null
    var id: Int = 0

    constructor(id: Int, mail: String, pass: Int) {
        this.pass = pass
        this.mail = mail
        this.id = id
    }

    constructor(mail: String, pass: Int) {
        this.pass = pass
        this.mail = mail
    }
}