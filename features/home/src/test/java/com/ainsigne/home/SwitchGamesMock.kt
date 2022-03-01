package com.ainsigne.home

import com.ainsigne.common.utils.extension.EMPTY
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.domain.entities.SwitchGameDomainEntities

object SwitchGamesMock {
    val switchGames = listOf(
        SwitchGameDomainEntities.SwitchGameData(
            id = "1",
            name = "#Breakforcist Battle",
            developers = listOf(
                "Lucid Sheep Games"
            ),
            publishers = listOf(
                "Lucid Sheep Games"
            ),
            releaseDates = SwitchGameDomainEntities.SwitchGameReleaseDate(
                japan = "Unreleased",
                northAmerica = "April 12, 2018",
                europe = "April 12, 2018",
                australia = "April 12, 2018"
            ),
            genre = listOf(
                "Party"
            )
        ),
        SwitchGameDomainEntities.SwitchGameData(
            id = "2",
            name = "#KillAllZombies",
            developers = listOf(
                "Beatshapers"
            ),
            publishers = listOf(
                "Beatshapers"
            ),
            releaseDates = SwitchGameDomainEntities.SwitchGameReleaseDate(
                japan = "Unreleased",
                northAmerica = "January 24, 2019",
                europe = "January 24, 2019",
                australia = "January 24, 2019"
            ),
            genre = listOf(
                "Survival",
                "third-person shooter"
            )
        )
    )
}