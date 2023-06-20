package com.pw3.coreapp.util

fun CharSequence.isValidEmail(): Boolean {
    // [a-zA-Z0-9._%+-]+ matches the local part of the email address before the @ symbol. It allows one or more lowercase letters, uppercase letters, digits, dot, underscore, percent, plus, or hyphen characters.
    //@ matches the @ symbol in the email address.
    //[a-zA-Z0-9.-]+ matches the domain part of the email address after the @ symbol. It allows one or more lowercase letters, uppercase letters, digits, dot, or hyphen characters.
    //\\. matches a literal dot (.) character. The backslash () is used to escape the dot because dot has a special meaning in regular expressions.
    //[a-zA-Z]{2,} matches the top-level domain part of the email address. It allows two or more lowercase letters or uppercase letters. This assumes that the top-level domain consists of two or more characters, such as ".com", ".net", or ".org".
    val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

    return matches(emailRegex)
}

fun CharSequence.isValidPassword(): Boolean {
    // (?=.*[a-zA-Z]) is a positive lookahead assertion that checks if there is at least one letter (uppercase or lowercase) present in the string.
    //.{6,} matches any character (.) and ensures that the length of the string is at least 6 ({6,}).
    val passwordRegex = Regex("(?=.*[a-zA-Z]).{6,}")

    return matches(passwordRegex)
}