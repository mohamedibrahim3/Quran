package com.mada.quranapplication.util

import com.mada.quranapplication.data.local.dao.SurahDao
import com.mada.quranapplication.data.local.entity.SurahEntity
import com.mada.quranapplication.presentation.util.CoroutineDispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class QuranDataInitializer @Inject constructor(
    private val dao: SurahDao,
    private val dispatchers: CoroutineDispatcherProvider
){
    suspend fun initializeData() {
        withContext(dispatchers.io) {
            // Check if data already exists
            val count = dao.getCount()
            if (count == 0) {
                // No data exists, insert initial data
                dao.insertAllSurahs(getInitialSurahData())
            }
        }
    }
    private fun getInitialSurahData(): List<SurahEntity> {
        return listOf(
            SurahEntity(1, "الفاتحة", 7, "Meccan", 1, 1, "page001"),
            SurahEntity(2, "البقرة", 286, "Medinan", 2, 49, "page002"),
            SurahEntity(3, "آل عمران", 200, "Medinan", 50, 76, "page050"),
            SurahEntity(4, "النساء", 176, "Medinan", 77, 106, "page077"),
            SurahEntity(5, "المائدة", 120, "Medinan", 106, 127, "page106"),
            SurahEntity(6, "الأنعام", 165, "Meccan", 128, 150, "page128"),
            SurahEntity(7, "الأعراف", 206, "Meccan", 151, 176, "page151"),
            SurahEntity(8, "الأنفال", 75, "Medinan", 177, 186, "page177"),
            SurahEntity(9, "التوبة", 129, "Medinan", 187, 207, "page187"),
            SurahEntity(10, "يونس", 109, "Meccan", 208, 221, "page208"),
            SurahEntity(11, "هود", 123, "Meccan", 221, 235, "page221"),
            SurahEntity(12, "يوسف", 111, "Meccan", 235, 248, "page235"),
            SurahEntity(13, "الرعد", 43, "Medinan", 249, 255, "page249"),
            SurahEntity(14, "إبراهيم", 52, "Meccan", 255, 261, "page255"),
            SurahEntity(15, "الحجر", 99, "Meccan", 262, 267, "page262"),
            SurahEntity(16, "النحل", 128, "Meccan", 267, 281, "page267"),
            SurahEntity(17, "الإسراء", 111, "Meccan", 282, 293, "page282"),
            SurahEntity(18, "الكهف", 110, "Meccan", 293, 304, "page293"),
            SurahEntity(19, "مريم", 98, "Meccan", 305, 312, "page305"),
            SurahEntity(20, "طه", 135, "Meccan", 312, 321, "page312"),
            SurahEntity(21, "الأنبياء", 112, "Meccan", 322, 331, "page322"),
            SurahEntity(22, "الحج", 78, "Medinan", 332, 341, "page332"),
            SurahEntity(23, "المؤمنون", 118, "Meccan", 342, 349, "page342"),
            SurahEntity(24, "النور", 64, "Medinan", 350, 359, "page350"),
            SurahEntity(25, "الفرقان", 77, "Meccan", 359, 366, "page359"),
            SurahEntity(26, "الشعراء", 227, "Meccan", 367, 376, "page367"),
            SurahEntity(27, "النمل", 93, "Meccan", 377, 385, "page377"),
            SurahEntity(28, "القصص", 88, "Meccan", 385, 396, "page385"),
            SurahEntity(29, "العنكبوت", 69, "Meccan", 396, 404, "page396"),
            SurahEntity(30, "الروم", 60, "Meccan", 404, 410, "page404"),
            SurahEntity(31, "لقمان", 34, "Meccan", 411, 414, "page411"),
            SurahEntity(32, "السجدة", 30, "Meccan", 415, 417, "page415"),
            SurahEntity(33, "الأحزاب", 73, "Medinan", 418, 427, "page418"),
            SurahEntity(34, "سبأ", 54, "Meccan", 428, 434, "page428"),
            SurahEntity(35, "فاطر", 45, "Meccan", 434, 440, "page434"),
            SurahEntity(36, "يس", 83, "Meccan", 440, 445, "page440"),
            SurahEntity(37, "الصافات", 182, "Meccan", 446, 452, "page446"),
            SurahEntity(38, "ص", 88, "Meccan", 453, 458, "page453"),
            SurahEntity(39, "الزمر", 75, "Meccan", 458, 467, "page458"),
            SurahEntity(40, "غافر", 85, "Meccan", 467, 476, "page467"),
            SurahEntity(41, "فصلت", 54, "Meccan", 477, 482, "page477"),
            SurahEntity(42, "الشورى", 53, "Meccan", 483, 489, "page483"),
            SurahEntity(43, "الزخرف", 89, "Meccan", 489, 495, "page489"),
            SurahEntity(44, "الدخان", 59, "Meccan", 496, 498, "page496"),
            SurahEntity(45, "الجاثية", 37, "Meccan", 499, 502, "page499"),
            SurahEntity(46, "الأحقاف", 35, "Meccan", 502, 506, "page502"),
            SurahEntity(47, "محمد", 38, "Medinan", 507, 510, "page507"),
            SurahEntity(48, "الفتح", 29, "Medinan", 511, 515, "page511"),
            SurahEntity(49, "الحجرات", 18, "Medinan", 515, 517, "page515"),
            SurahEntity(50, "ق", 45, "Meccan", 518, 520, "page518"),
            SurahEntity(51, "الذاريات", 60, "Meccan", 520, 523, "page520"),
            SurahEntity(52, "الطور", 49, "Meccan", 523, 525, "page523"),
            SurahEntity(53, "النجم", 62, "Meccan", 526, 528, "page526"),
            SurahEntity(54, "القمر", 55, "Meccan", 528, 531, "page528"),
            SurahEntity(55, "الرحمن", 78, "Medinan", 531, 534, "page531"),
            SurahEntity(56, "الواقعة", 96, "Meccan", 534, 537, "page534"),
            SurahEntity(57, "الحديد", 29, "Medinan", 537, 541, "page537"),
            SurahEntity(58, "المجادلة", 22, "Medinan", 542, 545, "page542"),
            SurahEntity(59, "الحشر", 24, "Medinan", 545, 548, "page545"),
            SurahEntity(60, "الممتحنة", 13, "Medinan", 549, 551, "page549"),
            SurahEntity(61, "الصف", 14, "Medinan", 551, 552, "page551"),
            SurahEntity(62, "الجمعة", 11, "Medinan", 553, 554, "page553"),
            SurahEntity(63, "المنافقون", 11, "Medinan", 554, 555, "page554"),
            SurahEntity(64, "التغابن", 18, "Medinan", 556, 557, "page556"),
            SurahEntity(65, "الطلاق", 12, "Medinan", 558, 559, "page558"),
            SurahEntity(66, "التحريم", 12, "Medinan", 560, 561, "page560"),
            SurahEntity(67, "الملك", 30, "Meccan", 562, 564, "page562"),
            SurahEntity(68, "القلم", 52, "Meccan", 564, 566, "page564"),
            SurahEntity(69, "الحاقة", 52, "Meccan", 566, 568, "page566"),
            SurahEntity(70, "المعارج", 44, "Meccan", 568, 570, "page568"),
            SurahEntity(71, "نوح", 28, "Meccan", 570, 571, "page570"),
            SurahEntity(72, "الجن", 28, "Meccan", 572, 573, "page572"),
            SurahEntity(73, "المزمل", 20, "Meccan", 574, 575, "page574"),
            SurahEntity(74, "المدثر", 56, "Meccan", 575, 577, "page575"),
            SurahEntity(75, "القيامة", 40, "Meccan", 577, 578, "page577"),
            SurahEntity(76, "الإنسان", 31, "Medinan", 578, 580, "page578"),
            SurahEntity(77, "المرسلات", 50, "Meccan", 580, 581, "page580"),
            SurahEntity(78, "النبأ", 40, "Meccan", 582, 583, "page582"),
            SurahEntity(79, "النازعات", 46, "Meccan", 583, 584, "page583"),
            SurahEntity(80, "عبس", 42, "Meccan", 585, 586, "page585"),
            SurahEntity(81, "التكوير", 29, "Meccan", 586, 586, "page586"),
            SurahEntity(82, "الانفطار", 19, "Meccan", 587, 587, "page587"),
            SurahEntity(83, "المطففين", 36, "Meccan", 587, 589, "page587"),
            SurahEntity(84, "الانشقاق", 25, "Meccan", 589, 590, "page589"),
            SurahEntity(85, "البروج", 22, "Meccan", 590, 590, "page590"),
            SurahEntity(86, "الطارق", 17, "Meccan", 591, 591, "page591"),
            SurahEntity(87, "الأعلى", 19, "Meccan", 591, 592, "page591"),
            SurahEntity(88, "الغاشية", 26, "Meccan", 592, 592, "page592"),
            SurahEntity(89, "الفجر", 30, "Meccan", 593, 594, "page593"),
            SurahEntity(90, "البلد", 20, "Meccan", 594, 594, "page594"),
            SurahEntity(91, "الشمس", 15, "Meccan", 595, 595, "page595"),
            SurahEntity(92, "الليل", 21, "Meccan", 595, 596, "page595"),
            SurahEntity(93, "الضحى", 11, "Meccan", 596, 596, "page596"),
            SurahEntity(94, "الشرح", 8, "Meccan", 596, 596, "page596"),
            SurahEntity(95, "التين", 8, "Meccan", 597, 597, "page597"),
            SurahEntity(96, "العلق", 19, "Meccan", 597, 597, "page597"),
            SurahEntity(97, "القدر", 5, "Meccan", 598, 598, "page598"),
            SurahEntity(98, "البينة", 8, "Medinan", 598, 599, "page598"),
            SurahEntity(99, "الزلزلة", 8, "Medinan", 599, 599, "page599"),
            SurahEntity(100, "العاديات", 11, "Meccan", 599, 600, "page599"),
            SurahEntity(101, "القارعة", 11, "Meccan", 600, 600, "page600"),
            SurahEntity(102, "التكاثر", 8, "Meccan", 600, 600, "page600"),
            SurahEntity(103, "العصر", 3, "Meccan", 601, 601, "page601"),
            SurahEntity(104, "الهمزة", 9, "Meccan", 601, 601, "page601"),
            SurahEntity(105, "الفيل", 5, "Meccan", 601, 601, "page601"),
            SurahEntity(106, "قريش", 4, "Meccan", 602, 602, "page602"),
            SurahEntity(107, "الماعون", 7, "Meccan", 602, 602, "page602"),
            SurahEntity(108, "الكوثر", 3, "Meccan", 602, 602, "page602"),
            SurahEntity(109, "الكافرون", 6, "Meccan", 603, 603, "page603"),
            SurahEntity(110, "النصر", 3, "Medinan", 603, 603, "page603"),
            SurahEntity(111, "المسد", 5, "Meccan", 603, 603, "page603"),
            SurahEntity(112, "الإخلاص", 4, "Meccan", 604, 604, "page604"),
            SurahEntity(113, "الفلق", 5, "Meccan", 604, 604, "page604"),
            SurahEntity(114, "الناس", 6, "Meccan", 604, 604, "page604")
        )
    }
}