package com.example.data.model

enum class QuizCategory(
    val displayName: String,
    val description: String,
    val iconName: String
) {
    ALL(
        displayName = "All Topics (Comprehensive)",
        description = "Full mixed question pool covering all sections of the DAR Reviewer.",
        iconName = "AutoAwesome"
    ),
    LAWS_AND_MANDATE(
        displayName = "Laws, Mandate & DAR Basics",
        description = "PD 27, RA 6657, RA 9700, RA 11953, Vision, Mission, Logo & 3 MFOs.",
        iconName = "Gavel"
    ),
    LAND_TENURE_LAD(
        displayName = "Land Tenure & LAD Milestones",
        description = "LTI, LAD Process, Claim Folder Routing (AO 4 s. 2014), Survey & Land Valuation.",
        iconName = "Landscape"
    ),
    BENEFICIARIES_RIGHTS(
        displayName = "Beneficiaries & Order of Priority",
        description = "Qualifications, Section 22 Order of Priority, Area of Award & Disqualifications.",
        iconName = "People"
    ),
    ALI_CASES_JURISDICTION(
        displayName = "ALI Cases & Case Build-Up",
        description = "AO 3 s. 2017, AO 6 s. 2017, NAMCI, Regional Director jurisdiction & JFIR.",
        iconName = "AccountBalance"
    ),
    DISQUALIFICATION_TRANSFER(
        displayName = "Disqualification & Transfers",
        description = "MC 19 s. 1996, AO 7 s. 2011, MC 19 s. 1978 death of ARB, and AO 8 s. 1995.",
        iconName = "SwapHoriz"
    ),
    LAND_TRANSFER_CLEARANCE(
        displayName = "Land Transfer Clearance (AO 4 s. 2021)",
        description = "LTC issuance rules, coverage, valid vs invalid transactions, pre-emption & redemption.",
        iconName = "Verified"
    ),
    PROJECT_SPLIT(
        displayName = "Project SPLIT",
        description = "Collective CLOA parcelization, components, budgets, regional hectarage targets.",
        iconName = "PieChart"
    ),
    ARBDSP_PROGRAMS(
        displayName = "ARBDSP & Post-Distribution",
        description = "RAISE the ARCs, WE FARM/GROW/BUILD, SIB, EDES, CRFPS, FBS & indicators.",
        iconName = "Grass"
    ),
    TENANCY_CASELAW(
        displayName = "Tenancy Requisites & Case Law",
        description = "Elements of tenancy, agricultural vs civil lease, landmark SC rulings (Zamoras, Oarde, etc.).",
        iconName = "MenuBook"
    ),
    CIVIL_SERVICE_ETHICS(
        displayName = "Civil Service, PRIME-HRM & RA 6713",
        description = "8 Norms of conduct, PRIME-HRM 4 pillars & maturity levels, RA 9184 procurement.",
        iconName = "Shield"
    )
}
