package com.example.data.model

data class FlipbookNoteSection(
    val id: String,
    val title: String,
    val pageNumber: Int,
    val category: String,
    val highlightPoints: List<Pair<String, String>>, // Label to Detail
    val summaryText: String
)

data class SelfTestQuestion(
    val id: Int,
    val prompt: String,
    val answer: String,
    val reference: String
)

object FlipbookContent {

    val selfTestList: List<SelfTestQuestion> = listOf(
        SelfTestQuestion(
            id = 1,
            prompt = "What are the three MFOs of DAR?",
            answer = "1. LTSP: Land Tenure Security Program\n2. AJDP: Agrarian Justice Delivery Program (adjudication, implementation, legal assistance)\n3. ARBDSP: Agrarian Reform Beneficiaries Development and Sustainability Program.",
            reference = "Page 3, DAR Basics"
        ),
        SelfTestQuestion(
            id = 2,
            prompt = "Retention limits under PD 27 and RA 6657?",
            answer = "• PD 27: Landowner retention up to 7 ha (tenanted rice/corn).\n• RA 6657: Landowner retention not more than 5 ha; plus 3 ha to each qualified child (at least 15 years old and actually tilling or managing).",
            reference = "Page 2 & 3, Cheat Sheet"
        ),
        SelfTestQuestion(
            id = 3,
            prompt = "Give the general qualifications of an ARB.",
            answer = "• Landless (owns less than 3 ha of agricultural land)\n• Filipino citizen\n• Resident of barangay (or municipality)\n• At least 15 years old at identification\n• Willing, able, and with aptitude to cultivate.",
            reference = "Page 2 & 8, Sec. 43 AO 7 s. 2011"
        ),
        SelfTestQuestion(
            id = 4,
            prompt = "What are the four milestones of land distribution?",
            answer = "1. Claim folder preparation and documentation\n2. Land survey\n3. EP/CLOA registration and distribution\n4. ARB installation.",
            reference = "Page 4, Milestones"
        ),
        SelfTestQuestion(
            id = 5,
            prompt = "When may awarded land be transferred?",
            answer = "General rule: Only by hereditary succession or to the government.\nExceptions: (1) 10 years from registration have lapsed, (2) Amortization fully paid, and (3) Transfer is to another qualified beneficiary. Transferee ceiling 5 ha.",
            reference = "Page 2 & 16, AO 8 s. 1995"
        ),
        SelfTestQuestion(
            id = 6,
            prompt = "Which transfer action does MC 19 s. 1978 cover?",
            answer = "Applies only if it involves an Emancipation Patent (EP) or Certificate of Land Transfer (CLT) and the ground is the DEATH of the tenant-beneficiary.",
            reference = "Page 15, MC 19 s. 1978"
        ),
        SelfTestQuestion(
            id = 7,
            prompt = "When does a farmholding revert to the government under MC 19 s. 1978?",
            answer = "Reversion occurs if: (1) The beneficiary dies without an heir, (2) There is no qualified heir, or (3) The heirs violate the rules.",
            reference = "Page 15, MC 19 s. 1978"
        ),
        SelfTestQuestion(
            id = 8,
            prompt = "What disqualifies an ARB for neglect?",
            answer = "Continuous neglect or abandonment of the awarded land for 2 calendar years, as determined by the Secretary or representative.",
            reference = "Page 2 & 13, MC 19 s. 1996"
        ),
        SelfTestQuestion(
            id = 9,
            prompt = "Amortization defaults that disqualify an ARB?",
            answer = "• Default on aggregate of 3 consecutive amortizations to landowner (VLT/DPS)\n• Failure to pay at least 3 annual amortizations to LBP (CA/VOS)\n• Failure to redeem or repurchase within 2 years.",
            reference = "Page 2 & 13, Sec. 49 AO 7 s. 2011"
        ),
        SelfTestQuestion(
            id = 10,
            prompt = "Who has primary jurisdiction over ALI cases, and what do MARPO and PARPO do?",
            answer = "The Regional Director (RD) has primary jurisdiction over all ALI cases. MARPO and PARPO authority is recommendatory and investigatory only.",
            reference = "Page 4 & 10, AO 3 s. 2017"
        ),
        SelfTestQuestion(
            id = 11,
            prompt = "What does NAMCI stand for in the ALI build-up?",
            answer = "Notice of mediation, Attendance sheet, Minutes of meeting, Certificate of posting, and Investigation report (JFIR).",
            reference = "Page 10, Sec. 16 AO 6 s. 2017"
        ),
        SelfTestQuestion(
            id = 12,
            prompt = "What is the twin requirement for a valid waiver of rights?",
            answer = "1. In favor of the DAR, government, or Land Bank.\n2. The ARB's consent is free and voluntary.",
            reference = "Page 11, Sec. 27 RA 6657; MC 4 s. 1983"
        ),
        SelfTestQuestion(
            id = 13,
            prompt = "What must happen before re-identification of a new ARB?",
            answer = "The disqualification case against the erring ARB must first be decided with finality by the Regional Director before the DARMO starts re-identification.",
            reference = "Page 11, AO 3 s. 2017"
        ),
        SelfTestQuestion(
            id = 14,
            prompt = "Does AO 4 s. 2021 cover awarded lands?",
            answer = "NO. AO 4 s. 2021 applies only to private agricultural lands with no Notice of Coverage issued, or not covered by any agrarian reform program. It does NOT apply to awarded lands (CLOA/EP).",
            reference = "Page 18, AO 4 s. 2021 Coverage"
        ),
        SelfTestQuestion(
            id = 15,
            prompt = "LTC fee, validity, and tenant redemption period?",
            answer = "• Filing fee: PhP 2,000 per land transaction\n• Validity: Signed LTC certification is effective for 6 months\n• Tenant redemption: 180 days from written notice of sale.",
            reference = "Page 2 & 19, AO 4 s. 2021"
        ),
        SelfTestQuestion(
            id = 16,
            prompt = "Which transactions can the ROD register without an LTC?",
            answer = "• Extrajudicial partition of property of person died before June 15, 1988\n• Partition of co-owned property before June 15, 1988\n• Subdivision without change of ownership\n• Real estate mortgage by landowner or beneficiary\n• LGU expropriation for actual, direct and exclusive public purpose.",
            reference = "Page 18, Valid Transactions without LTC"
        ),
        SelfTestQuestion(
            id = 17,
            prompt = "What does Project SPLIT do, and what is its target?",
            answer = "Project SPLIT parcelizes collective CLOAs into individual land titles to improve tenurial security. Total target: 1,368,883 hectares across 78 provinces.",
            reference = "Page 21, Project SPLIT"
        ),
        SelfTestQuestion(
            id = 18,
            prompt = "What are the three SPLIT components?",
            answer = "1. Parcelization of collective CLOAs (IT, regulatory framework, inventory, surveys)\n2. Capacity building and technical assistance\n3. Project management, monitoring & evaluation, and safeguards.",
            reference = "Page 21, Three Components"
        ),
        SelfTestQuestion(
            id = 19,
            prompt = "What does RAISE stand for in ARBDSP?",
            answer = "Responsive, Accelerated, Inclusive, Sustainable, and Equitable delivery of support services in Agrarian Reform Communities (ARCs).",
            reference = "Page 23, ARBDSP"
        ),
        SelfTestQuestion(
            id = 20,
            prompt = "Name the ARBDSP programs and their EDES sub-programs.",
            answer = "Programs: (1) Supervision & Management, (2) Social Infrastructure Building (SIB), (3) Enterprise Development & Economic Support (EDES), (4) Climate Resilient Farm Productivity Support (CRFPS).\nEDES sub-programs: 3.1.1 Product Dev, 3.1.2 VLFED, 3.1.3 PBD Lawyering, 3.1.4 LinkSFarMM, 3.1.5 Farm Business School (FBS), 3.1.6 PAHP, 3.2 Marketing, 3.3 Credit/MF, 3.4 PCIC, 3.5 RSBSA.",
            reference = "Page 23-26, ARBDSP Portfolio"
        ),
        SelfTestQuestion(
            id = 21,
            prompt = "What are the CY 2023 ARBDSP budget utilization targets?",
            answer = "• 80% BUR by end of 3rd quarter\n• 95% BUR by end of November 2023\n• 100% utilization and disbursement by year end.",
            reference = "Page 24, CY 2023 Intervention Focus"
        ),
        SelfTestQuestion(
            id = 22,
            prompt = "How many sessions does Farm Business School run?",
            answer = "25 sessions over a two-year period (Year 1 sessions and review, Year 2 technology adoption & operating enterprise).",
            reference = "Page 26, EDES 3.1.5"
        ),
        SelfTestQuestion(
            id = 23,
            prompt = "What are the Sec. 22 priority beneficiaries?",
            answer = "1. Agricultural lessees and share tenants\n2. Regular farmworkers\n3. Seasonal farmworkers\n4. Other farmworkers\n5. Actual tillers/occupants of public lands\n6. Collectives or cooperatives\n7. Others directly working on the land.",
            reference = "Page 8, Sec. 22 RA 6657"
        ),
        SelfTestQuestion(
            id = 24,
            prompt = "What are the elements of tenancy?",
            answer = "1. Parties are landholder and tenant\n2. Subject is agricultural land\n3. Consent (express or implied)\n4. Purpose is agricultural production\n5. Personal cultivation (with immediate farm household)\n6. Sharing of harvest or fixed compensation.",
            reference = "Page 9 & 28, Elements of Tenancy"
        ),
        SelfTestQuestion(
            id = 25,
            prompt = "What are the four HR pillars and the PRIME-HRM steps?",
            answer = "• 4 HR Pillars: (1) Recruitment/Selection/Placement, (2) Performance Management (SPMS), (3) Learning & Development, (4) Rewards & Recognition.\n• PRIME-HRM Steps: Assess, Assist, Award.\n• 4 Maturity Levels: Transactional, Process-Defined, Integrated, Strategic.",
            reference = "Page 30, Civil Service & PRIME-HRM"
        ),
        SelfTestQuestion(
            id = 26,
            prompt = "What are the six listed modes of procurement?",
            answer = "1. Competitive (public) bidding\n2. Direct contracting\n3. Shopping\n4. Small Value Procurement (SVP)\n5. Agency-to-agency\n6. Negotiated procurement.",
            reference = "Page 31, Modes of Procurement"
        )
    )

    val sections: List<FlipbookNoteSection> = listOf(
        FlipbookNoteSection(
            id = "sec_cheat_sheet",
            title = "Numbers and Deadlines Cheat Sheet",
            pageNumber = 2,
            category = "Core Timelines",
            highlightPoints = listOf(
                "PD 27" to "Oct 21, 1972. Family farm 5 ha unirrigated, 3 ha irrigated. Retention up to 7 ha.",
                "RA 6657 (CARL)" to "Signed Jun 10, 1988; effective Jun 15, 1988. Retention up to 5 ha + 3 ha per child (15+ yo).",
                "RA 9700 (CARPER)" to "July 1, 2009.",
                "RA 11953" to "New Agrarian Emancipation Act, Jul 7, 2023. Issues Certificate of Condonation.",
                "Landless limit" to "Owning less than 3 ha of agricultural land. At least 15yo.",
                "Disqualification" to "Neglect: 2 calendar years. Default: 3 consecutive VLT/DPS or 3 annual LBP amortizations. Repurchase: 2 years.",
                "Posting & Notices" to "Mediation notice 7 consecutive days. EJS published once a week for 3 weeks.",
                "Claim Folder" to "PARPO reviews within 5 working days. RD convenes PPU within 3 days. JFI 5-day notice.",
                "LTC (AO 4 s. 2021)" to "Fee PhP 2,000. Valid 6 months. MARPO cert 3 days. Tenant redemption 180 days."
            ),
            summaryText = "Master all critical numerical thresholds, statutory periods, and deadlines required for DAR qualification and promotional examinations."
        ),
        FlipbookNoteSection(
            id = "sec_tenancy",
            title = "Tenancy Requisites & Landmark Cases",
            pageNumber = 28,
            category = "Jurisprudence",
            highlightPoints = listOf(
                "Zamoras v. Su" to "Overseer of a coconut plantation is NOT a tenant (no sharing arrangement).",
                "Baranda v. Baguio" to "An owner tilling his own land is NOT a tenant.",
                "Oarde v. CA" to "DAR certifications of tenancy or non-tenancy are NOT conclusive on courts.",
                "Endaya v. CA" to "Successor-in-interest must recognize tenancy established prior to acquisition.",
                "Bonifacio v. Dizon" to "Tenant & immediate family must cultivate; cannot hire numerous outside persons.",
                "Gabriel v. Pangilinan" to "Tenancy ended when tenant stopped personally working fishpond.",
                "Primero v. CIR" to "Security of tenure: 'Once a tenant, always a tenant' unless ejected for legal cause.",
                "Cecilleville Realty" to "Only tenant-lessee has homelot right, not family members."
            ),
            summaryText = "Essential Supreme Court doctrines defining tenancy, security of tenure, and leasehold relationships under RA 3844 and RA 6657."
        ),
        FlipbookNoteSection(
            id = "sec_split",
            title = "Project SPLIT (Parcelization of CCLOAs)",
            pageNumber = 21,
            category = "Projects & Targets",
            highlightPoints = listOf(
                "Objective" to "Improve land tenure security and property rights by parcelizing collective CLOAs.",
                "Total Scope" to "1,368,883 hectares across 78 provinces (754k ha without ASP, 613k ha with ASP).",
                "Total Budget" to "PhP 24.62B (Loan PhP 19.24B / 78.13%, GOP PhP 5.38B / 21.87%).",
                "Top Region VIII" to "206,436 hectares (15.1% of total).",
                "Top Region VI" to "181,044 hectares (13.2% of total).",
                "Top Region XII" to "146,471 hectares (10.7% of total).",
                "3 Components" to "1. Parcelization of CCLOAs, 2. Capacity building, 3. Project management & safeguards."
            ),
            summaryText = "Key facts, component allocations, and regional target breakdown for the World Bank assisted Project SPLIT."
        ),
        FlipbookNoteSection(
            id = "sec_ethics",
            title = "Civil Service, PRIME-HRM & Ethics",
            pageNumber = 30,
            category = "Administration & Ethics",
            highlightPoints = listOf(
                "RA 6713 Norms" to "1. Public Interest, 2. Professionalism, 3. Justness, 4. Neutrality, 5. Responsiveness, 6. Nationalism, 7. Democracy, 8. Simple Living.",
                "PRIME-HRM Steps" to "Assess, Assist, Award (CSC MC 3 s. 2012).",
                "Maturity Levels" to "1. Transactional, 2. Process-Defined, 3. Integrated, 4. Strategic HRM.",
                "4 HR Pillars" to "Recruitment/Selection, Performance (SPMS), Learning & Dev, Rewards & Recognition.",
                "Procurement (RA 9184)" to "Shopping (PhP 1M), SVP (PhP 1M; PhilGEPS posting 3 days if ABC > 50k), Bidding."
            ),
            summaryText = "Civil Service Commission frameworks, PRIME-HRM indicators, government procurement thresholds, and ethical standards."
        )
    )
}
