package com.ainsigne.futurama

import com.ainsigne.common.utils.extension.EMPTY
import com.ainsigne.domain.entities.FuturamaDomainEntities

object FuturamaMock {
    val futuramaCharacters = listOf(
        FuturamaDomainEntities.FuturamaCharacterData(
            id = "1",
            name = FuturamaDomainEntities.FuturamaName(
                first = "Philip",
                middle = "Jay",
                last = "Fry"
            ),
            images = FuturamaDomainEntities.FuturamaImage(
                main = EMPTY,
                headShot = "https://upload.wikimedia.org/wikipedia/en/2/28/Philip_Fry.png"
            ),
            gender = "Male",
            species = "Human",
            occupation = "Intergalactic Delivery Boy"
        ),
        FuturamaDomainEntities.FuturamaCharacterData(
            id = "2",
            name = FuturamaDomainEntities.FuturamaName(
                first = "Turanga",
                middle = "",
                last = "Leela"
            ),
            images = FuturamaDomainEntities.FuturamaImage(
                main = EMPTY,
                headShot = "https://upload.wikimedia.org/wikipedia/en/d/d4/Turanga_Leela.png"
            ),
            gender = "Female",
            species = "Mutant",
            occupation = "Captain and pilot"
        )
    )
}