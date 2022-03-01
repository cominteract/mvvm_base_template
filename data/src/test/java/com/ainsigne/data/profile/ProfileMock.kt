package com.ainsigne.data.profile

import com.ainsigne.common.utils.extension.EMPTY
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.ProfileDomainEntities

object ProfileMock {
    val loggedProfile = ProfileDomainEntities.UserProfile(
        id = "1",
        name = ProfileDomainEntities.UserName(
            first = "Philip",
            middle = "Jay",
            last = "Fry"
        ),
        image = "https://upload.wikimedia.org/wikipedia/en/2/28/Philip_Fry.png",
        gender = "Male",
        occupation = "Intergalactic Delivery Boy",
        username = "user1",
        password = "password1"
    )

    val otherProfile = ProfileDomainEntities.UserProfile(
        id = "2",
        name = ProfileDomainEntities.UserName(
            first = "Turanga",
            middle = "",
            last = "Leela"
        ),
        image = "https://upload.wikimedia.org/wikipedia/en/d/d4/Turanga_Leela.png",
        gender = "Female",
        occupation = "Captain and pilot",
        username = "user2",
        password = "password2"
    )
}