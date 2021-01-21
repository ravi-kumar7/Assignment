package com.android.assignment.util

import com.android.assignment.data.model.Fact

object DummyData {

    fun getTitle(): String {
        return "DummyTitle"
    }

    fun getData(): ArrayList<Fact> {
        val data = ArrayList<Fact>()
        data.add(
            Fact(
                "Beavers",
                "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
                ""
            )
        )
        data.add(
            Fact(
                "Flag", "", ""
            )
        )
        data.add(
            Fact(
                "Transportation",
                "It is a well known fact that polar bears are the main mode of transportation in Canada. They consume far less gas and have the added benefit of being difficult to steal.",
                ""
            )
        )
        data.add(
            Fact(
                "Housing",
                "Warmer than you might think.",
                ""
            )
        )
        data.add(
            Fact(
                "Public Shame",
                "Sadly it's true.",
                ""
            )
        )
        data.add(
            Fact(
                "Kittens...",
                "Éare illegal. Cats are fine.",
                ""
            )
        )
        data.add(
            Fact(
                "Mounties",
                "They are the law. They are also Canada's foreign espionage service. Subtle.",
                ""
            )
        )

        data.add(
            Fact(
                "",
                "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
                ""
            ))
            return data
    }
}