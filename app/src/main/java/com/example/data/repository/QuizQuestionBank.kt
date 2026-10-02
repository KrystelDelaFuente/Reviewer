package com.example.data.repository

import com.example.data.model.DifficultyLevel
import com.example.data.model.QuizCategory
import com.example.data.model.QuizQuestion

object QuizQuestionBank {

    val allQuestions: List<QuizQuestion> = listOf(
        // ==========================================
        // BEGINNER LEVEL QUESTIONS
        // ==========================================
        QuizQuestion(
            id = "beg_01",
            questionText = "Under Presidential Decree No. 27 (PD 27), what is the maximum retention limit granted to a landowner?",
            options = listOf("Up to 3 hectares", "Up to 5 hectares", "Up to 7 hectares", "Up to 10 hectares"),
            correctIndex = 2,
            explanation = "Under PD 27 (promulgated Oct 21, 1972), the landowner may retain up to 7 hectares if cultivating it or if they will cultivate it.",
            legalBasis = "PD 27 (Oct 21, 1972)",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_02",
            questionText = "What is the standard family-size farm under PD 27 for tenanted rice and corn lands?",
            options = listOf(
                "3 ha unirrigated, 5 ha irrigated",
                "5 ha unirrigated, 3 ha irrigated",
                "5 ha regardless of irrigation",
                "7 ha unirrigated, 3 ha irrigated"
            ),
            correctIndex = 1,
            explanation = "Under PD 27, the tenant is deemed owner of a family-size farm: 5 hectares if unirrigated, and 3 hectares if irrigated.",
            legalBasis = "PD 27, Presidential Decree / Cheat Sheet",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_03",
            questionText = "When did the Comprehensive Agrarian Reform Law (RA 6657) officially take effect?",
            options = listOf("June 10, 1988", "June 15, 1988", "July 1, 2009", "October 21, 1972"),
            correctIndex = 1,
            explanation = "RA 6657 was signed on June 10, 1988, and took effect on June 15, 1988.",
            legalBasis = "RA 6657 Section 1 / Reviewer Page 2",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_04",
            questionText = "Under RA 6657 (CARL), what is the general retention limit for a landowner?",
            options = listOf("Not more than 3 hectares", "Not more than 5 hectares", "Not more than 7 hectares", "Not more than 12 hectares"),
            correctIndex = 1,
            explanation = "Under RA 6657, retention is not more than 5 hectares. Additionally, 3 hectares may go to each qualified child who is at least 15 years old and actually tilling or managing the farm.",
            legalBasis = "RA 6657 Section 6",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_05",
            questionText = "What official document is issued to agrarian reform beneficiaries under RA 11953 (New Agrarian Emancipation Act of July 7, 2023)?",
            options = listOf(
                "Certificate of Land Ownership Award (CLOA)",
                "Certificate of Condonation",
                "Emancipation Patent (EP)",
                "Certificate of Land Transfer (CLT)"
            ),
            correctIndex = 1,
            explanation = "RA 11953 (enacted July 7, 2023) provides for the condonation of agrarian reform debts and issues a 'Certificate of Condonation' to ARBs.",
            legalBasis = "RA 11953 (New Agrarian Emancipation Act)",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_06",
            questionText = "In the legal qualification of an Agrarian Reform Beneficiary (ARB), what does 'landless' strictly mean?",
            options = listOf(
                "Owning zero agricultural land whatsoever",
                "Owning less than 1 hectare of agricultural land",
                "Owning less than 3 hectares of agricultural land",
                "Owning less than 5 hectares of agricultural land"
            ),
            correctIndex = 2,
            explanation = "A 'landless' beneficiary means a farmer, tiller or farmworker who owns less than 3 hectares of agricultural land.",
            legalBasis = "Sec. 43, AO 7 s. 2011; Sec. 22, RA 6657",
            category = QuizCategory.BENEFICIARIES_RIGHTS,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_07",
            questionText = "What is the minimum age requirement for an ARB at the time of identification, screening, and selection?",
            options = listOf("At least 15 years old", "At least 18 years old", "At least 21 years old", "At least 16 years old"),
            correctIndex = 0,
            explanation = "Under AO 7 s. 2011 and RA 6657 rules, a qualified ARB must be at least 15 years old at the time of identification, screening, and selection.",
            legalBasis = "AO 7 s. 2011, Sec. 43 / Reviewer Page 2 & 8",
            category = QuizCategory.BENEFICIARIES_RIGHTS,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_08",
            questionText = "Which of the following represents the three Major Final Outputs (MFOs) of the Department of Agrarian Reform?",
            options = listOf(
                "LAD, VOS, and CA",
                "LTSP, AJDP, and ARBDSP",
                "SPLIT, RSBSA, and PAHP",
                "BARC, PARPO, and MARPO"
            ),
            correctIndex = 1,
            explanation = "DAR's three MFOs are: (1) LTSP (Land Tenure Security Program), (2) AJDP (Agrarian Justice Delivery Program), and (3) ARBDSP (Agrarian Reform Beneficiaries Development and Sustainability Program).",
            legalBasis = "DAR Mandate & MFOs / Reviewer Page 3",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 3
        ),
        QuizQuestion(
            id = "beg_09",
            questionText = "In the official DAR logo, what do the 12 sun segments symbolize?",
            options = listOf(
                "The 12 months of the farming calendar",
                "The original 12 administrative regions of the Philippines",
                "The 12 primary crops under agrarian reform",
                "The 12 core values of public servants"
            ),
            correctIndex = 1,
            explanation = "The sun in the DAR logo radiates light into a field of green divided into 12 segments representing the original 12 regions. Green symbolizes fertility and productivity; yellow symbolizes hope and the golden harvest.",
            legalBasis = "DAR Logo & Symbolism / Reviewer Page 3",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 3
        ),
        QuizQuestion(
            id = "beg_10",
            questionText = "Under RA 6657, which of the following agricultural lands is EXEMPT or EXCLUDED from CARP coverage?",
            options = listOf(
                "Private sugar cane plantations",
                "Government-owned rice lands",
                "Lands with 18% slope and over (unless already developed)",
                "Alienable and disposable public lands devoted to crops"
            ),
            correctIndex = 2,
            explanation = "Lands with 18% slope and over (except those already developed), national parks, forest reserves, school sites, and burial grounds are exempt/excluded.",
            legalBasis = "RA 6657 Sec. 10 / Reviewer Page 3",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 3
        ),
        QuizQuestion(
            id = "beg_11",
            questionText = "Under Section 22 of RA 6657, who occupies the FIRST order of priority among qualified agrarian reform beneficiaries?",
            options = listOf(
                "Regular farmworkers",
                "Seasonal farmworkers",
                "Agricultural lessees and share tenants",
                "Actual tillers or occupants of public lands"
            ),
            correctIndex = 2,
            explanation = "The first order of priority under Section 22 is: Agricultural lessees and share tenants, followed by regular farmworkers.",
            legalBasis = "RA 6657 Section 22 / Reviewer Page 8",
            category = QuizCategory.BENEFICIARIES_RIGHTS,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 8
        ),
        QuizQuestion(
            id = "beg_12",
            questionText = "How many consecutive calendar years of neglect or abandonment of awarded land will disqualify an ARB?",
            options = listOf("1 calendar year", "2 calendar years", "3 calendar years", "5 calendar years"),
            correctIndex = 1,
            explanation = "Continuous neglect or abandonment of awarded land for 2 calendar years as determined by the Secretary or representative is a ground for disqualification and title cancellation.",
            legalBasis = "MC 19 s. 1996; AO 7 s. 2011; Sec. 22 RA 6657",
            category = QuizCategory.DISQUALIFICATION_TRANSFER,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_13",
            questionText = "Republic Act No. 6713, enacted on Feb 20, 1989, is officially known as:",
            options = listOf(
                "The Comprehensive Agrarian Reform Law",
                "Code of Conduct and Ethical Standards for Public Officials and Employees",
                "Government Procurement Reform Act",
                "Civil Service Decree of the Philippines"
            ),
            correctIndex = 1,
            explanation = "RA 6713 is the Code of Conduct and Ethical Standards for Public Officials and Employees.",
            legalBasis = "RA 6713 (Feb 20, 1989) / Reviewer Page 2 & 30",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 30
        ),
        QuizQuestion(
            id = "beg_14",
            questionText = "Which norm of conduct under RA 6713 requires public servants to lead modest lives appropriate to their income and avoid ostentatious displays of wealth?",
            options = listOf("Professionalism", "Political Neutrality", "Simple Living", "Nationalism and Patriotism"),
            correctIndex = 2,
            explanation = "Norm #8 under RA 6713 is 'Simple Living': leading modest lives appropriate to position and income, with no ostentatious display of wealth.",
            legalBasis = "RA 6713 Norms of Conduct / Reviewer Page 31",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 31
        ),
        QuizQuestion(
            id = "beg_15",
            questionText = "What does the acronym 'SPLIT' stand for in Project SPLIT?",
            options = listOf(
                "Support to Parcelization of Lands for Individual Titling",
                "System for Partitioning Land Items and Titling",
                "Survey Program for Land parcels and Individual Tenants",
                "State Parcelization of Lands and Integrated Tenure"
            ),
            correctIndex = 0,
            explanation = "Project SPLIT stands for Support to Parcelization of Lands for Individual Titling.",
            legalBasis = "Project SPLIT AO 1 s. 2021 / Reviewer Page 21",
            category = QuizCategory.PROJECT_SPLIT,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 21
        ),
        QuizQuestion(
            id = "beg_16",
            questionText = "What are the four major milestones in the Land Acquisition and Distribution (LAD) process?",
            options = listOf(
                "Claim folder preparation, Land survey, EP/CLOA registration and distribution, ARB installation",
                "Notice of coverage, Land valuation, Bidding, Final certificate",
                "Mediation, Investigation, Payment, Reallocation",
                "Tax declaration, Assessor clearance, Leasehold contract, Titling"
            ),
            correctIndex = 0,
            explanation = "The 4 major milestones of land distribution are: (1) Claim folder preparation and documentation, (2) Land survey, (3) EP/CLOA registration and distribution, and (4) ARB installation.",
            legalBasis = "Four major milestones of land distribution / Reviewer Page 4",
            category = QuizCategory.LAND_TENURE_LAD,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 4
        ),
        QuizQuestion(
            id = "beg_17",
            questionText = "Under ARBDSP, what does the implementation strategy acronym 'RAISE the ARCs' stand for?",
            options = listOf(
                "Responsive, Accelerated, Inclusive, Sustainable, and Equitable",
                "Resilient, Agricultural, Innovative, Systematic, and Empowered",
                "Rapid, Accountable, Integrated, Social, and Ecological",
                "Reformed, Agrarian, Institutional, Secure, and Efficient"
            ),
            correctIndex = 0,
            explanation = "'RAISE the ARCs' stands for: responsive, accelerated, inclusive, sustainable and equitable delivery of support services in Agrarian Reform Communities.",
            legalBasis = "ARBDSP Big Picture / Reviewer Page 23",
            category = QuizCategory.ARBDSP_PROGRAMS,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 23
        ),
        QuizQuestion(
            id = "beg_18",
            questionText = "How many training sessions does the Farm Business School (FBS) run under the EDES sub-program?",
            options = listOf("10 sessions", "15 sessions", "25 sessions", "50 sessions"),
            correctIndex = 2,
            explanation = "Farm Business School (FBS) runs for 25 sessions over 2 years (Year 1 sessions and review, Year 2 technology adoption and operating enterprise).",
            legalBasis = "Sub-program 3.1.5 Farm Business School / Reviewer Page 26 & 32",
            category = QuizCategory.ARBDSP_PROGRAMS,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 26
        ),
        QuizQuestion(
            id = "beg_19",
            questionText = "As a general rule, when may awarded agricultural land be transferred?",
            options = listOf(
                "At any time upon mutual consent of buyer and seller",
                "Only after 10 years from registration, amortization fully paid, and to a qualified beneficiary",
                "After 2 years with municipal mayor approval",
                "Never under any circumstances"
            ),
            correctIndex = 1,
            explanation = "General rule: awarded land can only be transferred by hereditary succession or to the government. Exception: 10 years after registration with ROD, amortization fully paid, and to another qualified beneficiary.",
            legalBasis = "AO 8 s. 1995; Sec. 27 RA 6657 / Reviewer Page 2 & 16",
            category = QuizCategory.DISQUALIFICATION_TRANSFER,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 2
        ),
        QuizQuestion(
            id = "beg_20",
            questionText = "Who has primary jurisdiction over Agrarian Law Implementation (ALI) cases under AO 3 s. 2017?",
            options = listOf(
                "The Municipal Agrarian Reform Program Officer (MARPO)",
                "The Provincial Agrarian Reform Program Officer (PARPO)",
                "The Regional Director (RD)",
                "The Regional Trial Court (RTC)"
            ),
            correctIndex = 2,
            explanation = "The Regional Director (RD) has primary jurisdiction over all ALI cases. The authority of MARPO and PARPO is recommendatory and investigatory only.",
            legalBasis = "AO 3 s. 2017 Rule II Sec. 6 / Reviewer Page 4 & 10",
            category = QuizCategory.ALI_CASES_JURISDICTION,
            difficulty = DifficultyLevel.BEGINNER,
            pageReference = 10
        ),

        // ==========================================
        // INTERMEDIATE LEVEL QUESTIONS
        // ==========================================
        QuizQuestion(
            id = "int_01",
            questionText = "Under AO 4 s. 2014, within how many working days must the PARPO review the Claim Folder (CF) once received from the MARPO?",
            options = listOf("3 working days", "5 working days", "10 working days", "15 working days"),
            correctIndex = 1,
            explanation = "Under AO 4 s. 2014, the PARPO reviews the CF within 5 working days. If complete and correct, it is sent to the DARRO.",
            legalBasis = "AO 4 s. 2014 Step 1 / Reviewer Page 2 & 7",
            category = QuizCategory.LAND_TENURE_LAD,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 7
        ),
        QuizQuestion(
            id = "int_02",
            questionText = "When convening the Joint DAR-LBP Provincial Processing Unit (PPU), what is the mandatory notice period required before scheduling the Joint Field Investigation (JFI)?",
            options = listOf("2-day notice", "3-day notice", "5-day notice", "7-day notice"),
            correctIndex = 2,
            explanation = "The RD convenes the PPU within 3 days. If cleared, the RD and AOC Head schedule the JFI with a 5-day notice.",
            legalBasis = "AO 4 s. 2014 Step 2 / Reviewer Page 2 & 7",
            category = QuizCategory.LAND_TENURE_LAD,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 7
        ),
        QuizQuestion(
            id = "int_03",
            questionText = "Under AO 4 s. 2014, what key map requirement was removed during the Joint DAR-LBP Field Investigation (JFI)?",
            options = listOf(
                "Perimeter Land Use Map (PLUM)",
                "Approved Subdivision Plan (ASP)",
                "Land Classification Map (LC Map)",
                "Cadastral Map"
            ),
            correctIndex = 0,
            explanation = "Under AO 4 s. 2014, the perimeter land use map (PLUM) is no longer required; land use is shown per lot on the AdvSP or ASP.",
            legalBasis = "AO 4 s. 2014 Step 3 / Reviewer Page 7",
            category = QuizCategory.LAND_TENURE_LAD,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 7
        ),
        QuizQuestion(
            id = "int_04",
            questionText = "What is the filing fee for a Land Transfer Clearance (LTC) under AO 4 s. 2021, and how long is the issued LTC valid?",
            options = listOf(
                "PhP 1,000 and valid for 1 year",
                "PhP 2,000 and valid for 6 months",
                "PhP 3,500 and valid for 3 months",
                "PhP 5,000 and valid for 6 months"
            ),
            correctIndex = 1,
            explanation = "The LTC application fee is PhP 2,000 per land transaction, and the signed LTC certification is effective for 6 months.",
            legalBasis = "AO 4 s. 2021 Sec. 10 & Remedy / Reviewer Page 2 & 19",
            category = QuizCategory.LAND_TRANSFER_CLEARANCE,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 19
        ),
        QuizQuestion(
            id = "int_05",
            questionText = "Under AO 4 s. 2021, within how many days from written notice may an agricultural tenant-lessee exercise their right of redemption if land is sold without their knowledge?",
            options = listOf("30 days", "60 days", "90 days", "180 days"),
            correctIndex = 3,
            explanation = "The lessee may redeem at a reasonable price within 180 days from written notice served by the vendee on all lessees and the DAR upon registration of the sale.",
            legalBasis = "AO 4 s. 2021 Sec. 7 / Reviewer Page 2 & 19",
            category = QuizCategory.LAND_TRANSFER_CLEARANCE,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 19
        ),
        QuizQuestion(
            id = "int_06",
            questionText = "Which of the following transactions CAN the Registry of Deeds (ROD) register WITHOUT requiring a DAR Land Transfer Clearance (LTC)?",
            options = listOf(
                "Sale of agricultural land executed in 2015 respecting the 5 ha ceiling",
                "Transfer of a landowner's retention area",
                "Real estate mortgage by the original landowner or beneficiary",
                "Voluntary offer to sell private agricultural land"
            ),
            correctIndex = 2,
            explanation = "Registrable without an LTC: Real estate mortgage by original landowner/beneficiary; partition of co-owned property before Jun 15, 1988; EJS of person who died before Jun 15, 1988; subdivision without change of ownership; and LGU expropriation for public purpose.",
            legalBasis = "AO 4 s. 2021 Valid Transactions / Reviewer Page 18",
            category = QuizCategory.LAND_TRANSFER_CLEARANCE,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 18
        ),
        QuizQuestion(
            id = "int_07",
            questionText = "In the landmark Supreme Court case Zamoras v. Su (and Castillo v. CA), what was the ruling regarding an overseer of a coconut plantation?",
            options = listOf(
                "An overseer is automatically recognized as an agricultural share tenant",
                "An overseer of a coconut plantation is NOT a tenant because there is no personal cultivation and sharing arrangement",
                "An overseer is entitled to a 3-hectare family farm under PD 27",
                "An overseer has preferential pre-emption rights over lessees"
            ),
            correctIndex = 1,
            explanation = "In Zamoras v. Su and Castillo v. CA, the Supreme Court ruled that an overseer of a coconut plantation is not a tenant (no sharing arrangement and personal cultivation, only an employee/overseer).",
            legalBasis = "Zamoras v. Su; Castillo v. CA; Matienzo v. Servidad / Reviewer Page 28",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 28
        ),
        QuizQuestion(
            id = "int_08",
            questionText = "In Oarde v. CA (280 SCRA 235), what did the Supreme Court rule regarding DAR certifications of tenancy or non-tenancy?",
            options = listOf(
                "DAR certifications are final and unappealable in all courts",
                "DAR certifications of tenancy or non-tenancy are NOT conclusive upon courts",
                "DAR certifications supersede judicial findings in ejectment cases",
                "Only the MARPO can issue a binding tenancy certificate"
            ),
            correctIndex = 1,
            explanation = "In Oarde v. CA (280 SCRA 235), the Supreme Court ruled that DAR certifications of tenancy or non-tenancy are not conclusive upon the courts.",
            legalBasis = "Oarde v. CA (1997) / Reviewer Page 28 & 29",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 28
        ),
        QuizQuestion(
            id = "int_09",
            questionText = "In Baranda v. Baguio, what fundamental agrarian principle was established?",
            options = listOf(
                "A tenant who surrenders land may reclaim it within 1 year",
                "An owner tilling his own land is NOT a tenant",
                "Tenancy is hereditary up to the third degree of consanguinity",
                "A landowner cannot retain more than 3 hectares"
            ),
            correctIndex = 1,
            explanation = "Baranda v. Baguio held that an owner tilling his own land is not a tenant, as tenancy requires two distinct parties: the landholder and the tenant.",
            legalBasis = "Baranda v. Baguio / Reviewer Page 28",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 28
        ),
        QuizQuestion(
            id = "int_10",
            questionText = "Under Endaya v. CA, what is the obligation of a buyer or successor-in-interest who purchases tenanted agricultural land?",
            options = listOf(
                "The buyer may immediately evict the tenant upon registering the sale",
                "The successor-in-interest of the landholder must recognize tenancy established before acquiring the land",
                "The buyer is exempt from CARP coverage for 5 years",
                "The buyer must convert the lease into a civil law contract"
            ),
            correctIndex = 1,
            explanation = "In Endaya v. CA, the Supreme Court ruled that a successor-in-interest of the landholder must recognize and respect a tenancy established prior to acquiring the land.",
            legalBasis = "Endaya v. CA / Reviewer Page 28",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 28
        ),
        QuizQuestion(
            id = "int_11",
            questionText = "Which Supreme Court cases reaffirmed the doctrine of security of tenure summarized as 'Once a tenant, always a tenant'?",
            options = listOf(
                "Primero v. CIR; Pineda v. de Guzman",
                "Cecilleville Realty v. CA; Zamoras v. Su",
                "Gabriel v. Pangilinan; Teodoro v. Macaraeg",
                "Sutton v. DAR; Luz Farms v. DAR"
            ),
            correctIndex = 0,
            explanation = "Primero v. CIR and Pineda v. de Guzman held that once agricultural tenancy is established, the lessee may continue working the land unless ejected for legal cause ('Once a tenant, always a tenant').",
            legalBasis = "Primero v. CIR; Pineda v. de Guzman; De Jesus v. IAC / Reviewer Page 28",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 28
        ),
        QuizQuestion(
            id = "int_12",
            questionText = "Under MC 19 s. 1996 and AO 7 s. 2011, what amortization default will disqualify an ARB?",
            options = listOf(
                "1 unpaid monthly payment",
                "Default on an aggregate of 3 consecutive amortizations under VLT/DPS or 3 annual amortizations to LBP under CA/VOS",
                "Failure to pay within 60 days of harvesting",
                "Defaulting on personal co-op loans"
            ),
            correctIndex = 1,
            explanation = "An ARB is disqualified for default on an aggregate of 3 consecutive amortizations to the landowner (VLT/DPS) or 3 annual amortizations to LBP (CA/VOS), and failure to redeem or repurchase within 2 years.",
            legalBasis = "MC 19 s. 1996; AO 7 s. 2011 Sec. 49 / Reviewer Page 2 & 13",
            category = QuizCategory.DISQUALIFICATION_TRANSFER,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 13
        ),
        QuizQuestion(
            id = "int_13",
            questionText = "Under MC 4 s. 1983, what are the TWIN REQUIREMENTS for a valid waiver of rights by an ARB?",
            options = listOf(
                "(1) Executed before a notary, and (2) Payment of transfer tax",
                "(1) In favor of DAR, government, or Land Bank, and (2) ARB's consent is free and voluntary",
                "(1) Approved by the Barangay Captain, and (2) Registered with DTI",
                "(1) Notice to the former landowner, and (2) Payment of compensation"
            ),
            correctIndex = 1,
            explanation = "The twin requirements under MC 4 s. 1983 are: (1) The waiver is in favor of DAR, the government or Land Bank, and (2) The ARB's consent is free and voluntary. A valid waiver does not cause perpetual disqualification.",
            legalBasis = "Sec. 27 RA 6657; MC 4 s. 1983 / Reviewer Page 11 & 32",
            category = QuizCategory.ALI_CASES_JURISDICTION,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 11
        ),
        QuizQuestion(
            id = "int_14",
            questionText = "In the beneficiary selection process, within how many days must a farmer not listed on the preliminary list signify intent and submit documents?",
            options = listOf("3 days", "7 days", "15 days", "30 days"),
            correctIndex = 1,
            explanation = "Those who believe they qualify but were omitted have 7 days from receipt to signify their intent and submit supporting documents.",
            legalBasis = "AO 7 s. 2011; AO 3 s. 2012 / Reviewer Page 2 & 9",
            category = QuizCategory.BENEFICIARIES_RIGHTS,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 9
        ),
        QuizQuestion(
            id = "int_15",
            questionText = "Under ARBDSP guidelines, what is the mandatory minimum percentage of WOMEN ARBs that must be included as beneficiaries in SIB, EDES, and CRFPS?",
            options = listOf("At least 10%", "At least 15%", "At least 25%", "At least 50%"),
            correctIndex = 2,
            explanation = "CY 2023 intervention focus mandates: At least 25% women ARBs among beneficiaries of SIB, EDES and CRFPS, with priority for widows, solo parents, seniors, PWDs, IPs and youth.",
            legalBasis = "ARBDSP CY 2023 Focus / Reviewer Page 24",
            category = QuizCategory.ARBDSP_PROGRAMS,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 24
        ),
        QuizQuestion(
            id = "int_16",
            questionText = "Under PRIME-HRM (CSC MC 3 s. 2012), what are the three progressive steps in developing agency HRM excellence?",
            options = listOf(
                "Plan, Do, Check",
                "Assess, Assist, Award",
                "Recruit, Train, Promote",
                "Initiate, Execute, Evaluate"
            ),
            correctIndex = 1,
            explanation = "PRIME-HRM has 3 steps: (1) Assess (maturity in 4 HR systems), (2) Assist (customized technical assistance), and (3) Award (citations for HR excellence).",
            legalBasis = "PRIME-HRM (CSC MC 3 s. 2012) / Reviewer Page 30 & 32",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 30
        ),
        QuizQuestion(
            id = "int_17",
            questionText = "Under Republic Act No. 9184, what is the threshold for Shopping and Small Value Procurement (SVP) for National Government Agencies in the compendium?",
            options = listOf("PhP 250,000", "PhP 500,000", "PhP 1,000,000", "PhP 2,000,000"),
            correctIndex = 2,
            explanation = "In the compendium table, the threshold for Shopping (ordinary off-the-shelf goods) and Small Value Procurement (SVP) for NGAs, GOCCs, and GFIs is PhP 1,000,000.",
            legalBasis = "Modes of Procurement / Reviewer Page 31",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 31
        ),
        QuizQuestion(
            id = "int_18",
            questionText = "For Small Value Procurement (SVP) under RA 9184, when is PhilGEPS posting required for at least 3 calendar days?",
            options = listOf(
                "For all procurements regardless of amount",
                "If the Approved Budget for the Contract (ABC) is above PhP 50,000",
                "If the ABC is above PhP 500,000",
                "Only during fourth quarter purchases"
            ),
            correctIndex = 1,
            explanation = "PhilGEPS posting is required for at least 3 calendar days if the ABC is above PhP 50,000.",
            legalBasis = "Modes of Procurement RA 9184 / Reviewer Page 31",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 31
        ),
        QuizQuestion(
            id = "int_19",
            questionText = "Under MC 19 s. 1978, when a tenant-beneficiary dies, within what period must the surviving heirs choose the single owner-cultivator?",
            options = listOf("Within 15 days of death", "Within 1 month of death", "Within 60 days of death", "Within 6 months of death"),
            correctIndex = 1,
            explanation = "If there are several heirs and no extrajudicial settlement, they must choose the owner-cultivator within 1 month of death. The surviving spouse has first preference.",
            legalBasis = "MC 19 s. 1978 / Reviewer Page 15",
            category = QuizCategory.DISQUALIFICATION_TRANSFER,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 15
        ),
        QuizQuestion(
            id = "int_20",
            questionText = "Which administrative issuance serves as the revised rules governing the Land Acquisition and Distribution (LAD) of private agricultural lands?",
            options = listOf("AO 7 s. 2011", "AO 4 s. 2021", "AO 3 s. 2017", "AO 1 s. 2021"),
            correctIndex = 0,
            explanation = "AO 7 s. 2011 is the governing revised rules for LAD of private agricultural lands, as amended by AO 3 s. 2012 and AO 4 s. 2014.",
            legalBasis = "AO 7 s. 2011 / Reviewer Page 5",
            category = QuizCategory.LAND_TENURE_LAD,
            difficulty = DifficultyLevel.INTERMEDIATE,
            pageReference = 5
        ),

        // ==========================================
        // ADVANCED LEVEL QUESTIONS
        // ==========================================
        QuizQuestion(
            id = "adv_01",
            questionText = "In an Agrarian Law Implementation (ALI) case build-up under AO 3 s. 2017 & AO 6 s. 2017, what does the acronym 'NAMCI' stand for?",
            options = listOf(
                "Notice, Attendance sheet, Minutes of meeting, Certificate of posting, Investigation report",
                "New Agrarian Mandate, Clearance, Inspection, and Certificate",
                "Notice of Assessment, Mediation Compromise, and Implementation",
                "National Agrarian Monitoring Committee for Investigation"
            ),
            correctIndex = 0,
            explanation = "NAMCI stands for: (1) Notice of mediation, (2) Attendance sheet, (3) Minutes of the meeting, (4) Certificate of posting, and (5) Investigation report (Joint Field Investigation Report - JFIR).",
            legalBasis = "AO 6 s. 2017 Sec. 16; AO 3 s. 2017 / Reviewer Page 10 & 32",
            category = QuizCategory.ALI_CASES_JURISDICTION,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 10
        ),
        QuizQuestion(
            id = "adv_02",
            questionText = "Regarding the Joint Field Investigation Report (JFIR) in an ALI case, which rule applies while the case is pending or not yet final and executory?",
            options = listOf(
                "It is a public record accessible immediately to all tenants",
                "It is strictly confidential; only the PARPO II may receive it",
                "It must be published in a provincial newspaper for 3 consecutive weeks",
                "Copies must be furnished immediately to both private parties"
            ),
            correctIndex = 1,
            explanation = "The JFIR is confidential while the case is pending or not yet final and executory; only the PARPO II may receive it (DAR MC 07-2011 as amended by MC 02-2016).",
            legalBasis = "DAR MC 07-2011 as amended by MC 02-2016 / Reviewer Page 10",
            category = QuizCategory.ALI_CASES_JURISDICTION,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 10
        ),
        QuizQuestion(
            id = "adv_03",
            questionText = "What is the crucial legal distinction between 'Disqualification of an ARB' and 'Reallocation of a Potential Beneficiary'?",
            options = listOf(
                "Disqualification is handled by the MARPO, while Reallocation is handled by the RTC",
                "Disqualification is processed under AO 3 s. 2017, whereas Reallocation of a potential beneficiary is processed under AO 7 s. 2011 and does not go through AO 3 s. 2017",
                "Reallocation requires an act of Congress, while Disqualification requires a Barangay resolution",
                "Disqualification applies only to corporate farms, while Reallocation applies to rice lands"
            ),
            correctIndex = 1,
            explanation = "Disqualification of an installed ARB is an ALI case processed under AO 3 s. 2017. Reallocation of a potential beneficiary prior to title issuance is processed under AO 7 s. 2011 as amended and does not go through AO 3 s. 2017.",
            legalBasis = "AO 3 s. 2017 vs AO 7 s. 2011 / Reviewer Page 11 & 32",
            category = QuizCategory.ALI_CASES_JURISDICTION,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 11
        ),
        QuizQuestion(
            id = "adv_04",
            questionText = "Before the DARMO can start the re-identification of a new ARB to replace a disqualified one, what condition must first be met?",
            options = listOf(
                "The former landowner must give written consent",
                "The disqualification case must first be decided with finality by the Regional Director",
                "The local cooperative must dissolve its charter",
                "A 5-year cooling period must elapse"
            ),
            correctIndex = 1,
            explanation = "The disqualification case must first be decided with finality by the Regional Director before the DARMO starts re-identification of a new ARB.",
            legalBasis = "Disqualification versus Reallocation / Reviewer Page 11 & 32",
            category = QuizCategory.ALI_CASES_JURISDICTION,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 11
        ),
        QuizQuestion(
            id = "adv_05",
            questionText = "Who holds the authority to issue the Order of Cancellation of a disqualified ARB's EP or CLOA?",
            options = listOf(
                "The Municipal Agrarian Reform Program Officer (MARPO)",
                "The Provincial Agrarian Reform Adjudicator (PARAD)",
                "The DAR Secretary",
                "The Land Registration Authority Administrator"
            ),
            correctIndex = 2,
            explanation = "Once the DAR Secretary issues an Order of Cancellation and the ROD has implemented it, DAR through the LTID can issue a new EP/CLOA to the re-identified ARB.",
            legalBasis = "Disqualification versus Reallocation / Reviewer Page 11",
            category = QuizCategory.ALI_CASES_JURISDICTION,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 11
        ),
        QuizQuestion(
            id = "adv_06",
            questionText = "What is the total project cost and hectare scope of Project SPLIT (parcelization of collective CLOAs)?",
            options = listOf(
                "PhP 10.5 billion for 500,000 hectares",
                "PhP 19.24 billion for 1,000,000 hectares",
                "PhP 24.62 billion (Loan: PhP 19.24B / GOP: PhP 5.38B) for 1,368,883 hectares",
                "PhP 30.00 billion for 2,000,000 hectares"
            ),
            correctIndex = 2,
            explanation = "Project SPLIT total cost is PhP 24.62 billion (Loan: PhP 19.24B / 78.13%, GOP counterpart: PhP 5.38B / 21.87%). Total scope is 1,368,883 hectares (754,995 ha without approved survey plan + 613,888 ha with approved plan).",
            legalBasis = "Project SPLIT Scope, Cost and Duration / Reviewer Page 21 & 32",
            category = QuizCategory.PROJECT_SPLIT,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 21
        ),
        QuizQuestion(
            id = "adv_07",
            questionText = "Which administrative region has the LARGEST target hectarage under Project SPLIT?",
            options = listOf(
                "Region VI (Western Visayas) with 181,044 ha",
                "Region VIII (Eastern Visayas) with 206,436 ha (15.1%)",
                "Region XII (SOCCSKSARGEN) with 146,471 ha",
                "Region V (Bicol) with 100,697 ha"
            ),
            correctIndex = 1,
            explanation = "Region VIII has the largest share with 206,436 hectares (15.1% of total), followed by Region VI (181,044 ha / 13.2%) and Region XII (146,471 ha / 10.7%).",
            legalBasis = "Project SPLIT Biggest Regional Targets / Reviewer Page 22",
            category = QuizCategory.PROJECT_SPLIT,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 22
        ),
        QuizQuestion(
            id = "adv_08",
            questionText = "In Pre-OCI and projection, what rule applies to a landholding located in timberland that is covered by an existing title?",
            options = listOf(
                "All titles in timberland are automatically null and void with no exceptions",
                "If titled on or before July 1, 1919 (Act 2874, Public Land Act), proceed with coverage; if titled after that date, discontinue and refer to DARCO to validate with DENR",
                "Immediately issue an EP to the occupants",
                "Coverage proceeds regardless of title date as long as crops are planted"
            ),
            correctIndex = 1,
            explanation = "If titled on or before July 1, 1919 (Public Land Act, Act 2874), proceed with coverage. If titled after that date, discontinue coverage and refer to DARCO, which makes representations with DENR to validate the title.",
            legalBasis = "Pre-OCI, Projection and Survey Rules / Reviewer Page 6",
            category = QuizCategory.LAND_TENURE_LAD,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 6
        ),
        QuizQuestion(
            id = "adv_09",
            questionText = "Under PRIME-HRM (CSC MC 3 s. 2012), what are the four progressive HRM Maturity Levels?",
            options = listOf(
                "Level 1: Basic, Level 2: Intermediate, Level 3: Advance, Level 4: Superior",
                "Transactional HRM, Process-Defined HRM, Integrated HRM, Strategic HRM",
                "Forming, Storming, Norming, Performing",
                "Recruitment, Performance, Training, Rewards"
            ),
            correctIndex = 1,
            explanation = "The four PRIME-HRM maturity levels are: (1) Transactional HRM, (2) Process-Defined HRM, (3) Integrated HRM, and (4) Strategic HRM (where HR strategy is part of agency strategy).",
            legalBasis = "PRIME-HRM (CSC MC 3 s. 2012) / Reviewer Page 30 & 32",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 30
        ),
        QuizQuestion(
            id = "adv_10",
            questionText = "What are the four Human Resource pillars evaluated under PRIME-HRM?",
            options = listOf(
                "Salaries, Allowances, Overtime, and Pension",
                "Recruitment/Selection/Placement, Performance Management (SPMS), Learning and Development, and Rewards and Recognition",
                "Legal, Administrative, Financial, and Operational",
                "Planning, Budgeting, Execution, and Auditing"
            ),
            correctIndex = 1,
            explanation = "The four HR pillars are: (1) Recruitment, selection and placement, (2) Performance management (IPCRs, DPCRs, DAR SPMS), (3) Learning and development, and (4) Rewards and recognition.",
            legalBasis = "Four HR Pillars / Reviewer Page 30 & 32",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 30
        ),
        QuizQuestion(
            id = "adv_11",
            questionText = "In Gabriel v. Pangilinan, what caused the agricultural tenancy relationship over a fishpond to legally terminate?",
            options = listOf(
                "Increase in land taxes by the municipality",
                "Tenancy ended when the tenant and household stopped personally working the fishpond",
                "The conversion of fishpond to brackish water",
                "A change in ownership through bank foreclosure"
            ),
            correctIndex = 1,
            explanation = "In Gabriel v. Pangilinan, the Supreme Court ruled that tenancy ended when the tenant and his household stopped personally working the fishpond, as personal cultivation is an indispensable element.",
            legalBasis = "Gabriel v. Pangilinan / Reviewer Page 28",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 28
        ),
        QuizQuestion(
            id = "adv_12",
            questionText = "In Cecilleville Realty v. CA, what did the Supreme Court hold regarding the right to a homelot in agricultural lands?",
            options = listOf(
                "All relatives and extended family members of the tenant are entitled to homelots",
                "Only the tenant-lessee has a right to a homelot, not other family members",
                "The landowner is required to construct houses for all farmworkers",
                "Homelots can be mortgaged to commercial banks"
            ),
            correctIndex = 1,
            explanation = "In Cecilleville Realty v. CA, the Supreme Court clarified that only the tenant-lessee has a right to a homelot, not family members.",
            legalBasis = "Cecilleville Realty v. CA / Reviewer Page 28",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 28
        ),
        QuizQuestion(
            id = "adv_13",
            questionText = "Under EDES Sub-program 3.1.1 (Product Development), how are meat products like tocino, embotido, and longganisa counted for accomplishment reporting?",
            options = listOf(
                "Each distinct recipe counts as a separate product (3 products total)",
                "All processed meat products derived from the same raw material count as only 1 product",
                "They only count if packaged in vacuum seals",
                "They are classified as fresh agricultural produce"
            ),
            correctIndex = 1,
            explanation = "Under EDES 3.1.1, new product means raw material processed into another form. Same raw material in different forms: Meat products (tocino, embotido, longganisa) count as 1 product; banana-based pastries count as 1.",
            legalBasis = "EDES Sub-program 3.1.1 Product Development / Reviewer Page 25",
            category = QuizCategory.ARBDSP_PROGRAMS,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 25
        ),
        QuizQuestion(
            id = "adv_14",
            questionText = "What are the CY 2023 Budget Utilization Rate (BUR) milestones mandated for the ARBDSP sector?",
            options = listOf(
                "50% by Q1, 75% by Q2, 100% by Q3",
                "80% BUR by end of 3rd quarter; 95% BUR by end of November 2023; 100% utilization and disbursement by year end",
                "70% by mid-year, 90% by October, 98% by December",
                "No specific targets as long as funds do not revert"
            ),
            correctIndex = 1,
            explanation = "Budget utilization targets: 80% BUR by end of 3rd quarter; 95% BUR by end of November 2023; 100% utilization and disbursement by year end.",
            legalBasis = "ARBDSP CY 2023 Intervention Focus / Reviewer Page 24 & 32",
            category = QuizCategory.ARBDSP_PROGRAMS,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 24
        ),
        QuizQuestion(
            id = "adv_15",
            questionText = "Under AO 8 s. 1995, if awarded agricultural land is transferred through a Deed of Extrajudicial Settlement (EJS), what publication requirement must be fulfilled?",
            options = listOf(
                "Posted on the DAR website for 30 consecutive days",
                "Published in a newspaper of general circulation in the province once a week for 3 consecutive weeks",
                "Sent via registered mail to all barangay captains in the region",
                "Announced over local radio stations for 5 consecutive mornings"
            ),
            correctIndex = 1,
            explanation = "Deed of Extrajudicial Settlement must be published in a newspaper of general circulation in the province once a week for 3 consecutive weeks and registered with the ROD.",
            legalBasis = "AO 8 s. 1995; Rule 74 Sec. 1 Rules of Court / Reviewer Page 2 & 16",
            category = QuizCategory.DISQUALIFICATION_TRANSFER,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 16
        ),
        QuizQuestion(
            id = "adv_16",
            questionText = "In the Four Performance-Based Bonus (PBB) Accountabilities for FY 2023, which hotline services are specifically monitored for citizen/client satisfaction results?",
            options = listOf(
                "Hotline 911 and DILG Patrol 117",
                "Hotline 8888 and Contact Center ng Bayan (CCB)",
                "Civil Service Helpline and Ombudsman Action Center",
                "Presidential Complaint Center (PCC) alone"
            ),
            correctIndex = 1,
            explanation = "PBB Accountabilities #4: Citizen/client satisfaction results require resolving complaints from Hotline 8888 and the Contact Center ng Bayan (CCB).",
            legalBasis = "PBB Accountabilities (FY 2023) / Reviewer Page 31",
            category = QuizCategory.CIVIL_SERVICE_ETHICS,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 31
        ),
        QuizQuestion(
            id = "adv_17",
            questionText = "Under AO 4 s. 2021, if an applicant is aggrieved by the denial of a Land Transfer Clearance (LTC) by the PARPO II, what is their legal remedy?",
            options = listOf(
                "File an immediate Petition for Certiorari directly before the Supreme Court",
                "File an original ALI case before the Regional Director under AO 3 s. 2017",
                "Appeal to the Municipal Agrarian Reform Officer within 5 days",
                "Submit a formal objection to the Land Bank of the Philippines"
            ),
            correctIndex = 1,
            explanation = "Remedy under AO 4 s. 2021: The applicant or any adversely affected person files an original ALI case before the Regional Director under AO 3 s. 2017.",
            legalBasis = "AO 4 s. 2021 Remedy, Validity, Revocation / Reviewer Page 19",
            category = QuizCategory.LAND_TRANSFER_CLEARANCE,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 19
        ),
        QuizQuestion(
            id = "adv_18",
            questionText = "Under Teodoro v. Macaraeg and Nisnisan v. CA, what are legal causes for the termination of agricultural leasehold?",
            options = listOf(
                "Increase in bank interest rates",
                "Abandonment of landholding without lessor's knowledge, and voluntary surrender by lessee with written notice served 3 months in advance",
                "The landowner's desire to build a private family resort",
                "Refusal of the tenant to adopt new high-yield variety seeds"
            ),
            correctIndex = 1,
            explanation = "Causes for termination of leasehold include: (1) Abandonment of the landholding without the lessor's knowledge (Teodoro v. Macaraeg), (2) Voluntary surrender by the lessee with written notice 3 months in advance (Nisnisan v. CA), and (3) No heir to succeed on death or incapacity (Sec. 8 RA 3844).",
            legalBasis = "Teodoro v. Macaraeg; Nisnisan v. CA; Sec. 8 RA 3844 / Reviewer Page 28",
            category = QuizCategory.TENANCY_CASELAW,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 28
        ),
        QuizQuestion(
            id = "adv_19",
            questionText = "Under MC 19 s. 1978, how many total documentary requirements must be submitted to the DARMO for a transfer action due to the death of a tenant-beneficiary?",
            options = listOf("8 documents", "12 documents", "15 documents", "18 documents"),
            correctIndex = 3,
            explanation = "Under MC 19 s. 1978, there are 18 documentary requirements, including the written request, death certificate, survey plan, coop membership certification, and Actual Tiller's Deed of Undertaking (ATDU).",
            legalBasis = "MC 19 s. 1978 Documentary Requirements (18) / Reviewer Page 15",
            category = QuizCategory.DISQUALIFICATION_TRANSFER,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 15
        ),
        QuizQuestion(
            id = "adv_20",
            questionText = "What was the landmark holding in the Sutton and Luz Farms cases concerning agricultural land coverage under CARP?",
            options = listOf(
                "All private farmlands must be subdivided within 180 days",
                "Lands devoted to raising livestock, poultry and swine are NOT agricultural lands and are excluded from CARP coverage",
                "Corporate plantations cannot retain any acreage",
                "Tenants of livestock farms become owners of the animals"
            ),
            correctIndex = 1,
            explanation = "In Luz Farms v. Secretary of DAR and Department of Agrarian Reform v. Sutton, the Supreme Court ruled that lands devoted to raising livestock, poultry, and swine are not agricultural lands and therefore excluded from CARP.",
            legalBasis = "Luz Farms v. Sec of DAR (1990); DAR v. Sutton (2005) / Reviewer Page 4",
            category = QuizCategory.LAWS_AND_MANDATE,
            difficulty = DifficultyLevel.ADVANCED,
            pageReference = 4
        )
    )

    fun getQuestionsByDifficulty(difficulty: DifficultyLevel?): List<QuizQuestion> {
        if (difficulty == null) return allQuestions
        return allQuestions.filter { it.difficulty == difficulty }
    }

    fun getQuestionsByCategory(category: QuizCategory, difficulty: DifficultyLevel? = null): List<QuizQuestion> {
        val filteredByCat = if (category == QuizCategory.ALL) allQuestions else allQuestions.filter { it.category == category }
        if (difficulty == null) return filteredByCat
        return filteredByCat.filter { it.difficulty == difficulty }
    }

    fun getRandomSample(
        count: Int,
        difficulty: DifficultyLevel? = null,
        category: QuizCategory = QuizCategory.ALL
    ): List<QuizQuestion> {
        val pool = getQuestionsByCategory(category, difficulty)
        return pool.shuffled().take(count)
    }

    fun getQuestionById(id: String): QuizQuestion? {
        return allQuestions.firstOrNull { it.id == id }
    }
}
