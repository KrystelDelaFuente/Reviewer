package com.example.data.model

enum class DifficultyLevel(
    val title: String,
    val subtitle: String,
    val description: String,
    val recommendedSeconds: Int
) {
    BEGINNER(
        title = "Beginner",
        subtitle = "Foundations, Core Concepts & Key Dates",
        description = "Covers fundamental agrarian laws (PD 27, RA 6657, RA 11953), basic retention limits, DAR vision/mission/MFOs, and Civil Service ethics.",
        recommendedSeconds = 30
    ),
    INTERMEDIATE(
        title = "Intermediate",
        subtitle = "Procedures, Timelines & Jurisprudence",
        description = "Covers Claim Folder routing (AO 4 s. 2014), Land Transfer Clearance (AO 4 s. 2021), Order of Priority (Sec. 22), tenancy requisites, and Supreme Court rulings.",
        recommendedSeconds = 30
    ),
    ADVANCED(
        title = "Advanced",
        subtitle = "Deep Admin Orders & Case Build-Up",
        description = "Covers ALI case build-up (AO 3 s. 2017 & AO 6 s. 2017), Project SPLIT components and budgets, procurement thresholds, and tricky legal scenarios.",
        recommendedSeconds = 45
    );

    companion object {
        fun fromString(name: String?): DifficultyLevel {
            return entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: BEGINNER
        }
    }
}
