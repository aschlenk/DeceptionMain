\ENCODING=ISO-8859-1
\Problem name: DECEPTION

Maximize
 obj: d
Subject To
 UTILITY_TF_7#0:     2 sigma_k10_o7 + 10 sigma_k11_o7 + 10 sigma_k12_o7
                     + Z_k10_o7 + Z_k11_o7 + Z_k12_o7 <= 0
 UTILITY_TF_8#1:     2 sigma_k10_o8 + 10 sigma_k11_o8 + 10 sigma_k12_o8
                     + Z_k10_o8 + Z_k11_o8 + Z_k12_o8 <= 0
 _EQ_K10#2:          sigma_k10_o7 + sigma_k10_o8  = 1
 _EQ_K11#3:          sigma_k11_o7 + sigma_k11_o8  = 1
 _EQ_K12#4:          sigma_k12_o7 + sigma_k12_o8  = 1
 Z1_LEFT_K10_O7#5:   - Z_k10_o7 <= 10
 Z1_RIGHT_K10_O7#6:  Z_k10_o7 <= 0
 Z1_LEFT_K10_O8#7:   - Z_k10_o8 <= 10
 Z1_RIGHT_K10_O8#8:  Z_k10_o8 <= 0
 Z1_LEFT_K11_O7#9:   - Z_k11_o7 <= 10
 Z1_RIGHT_K11_O7#10: Z_k11_o7 <= 0
 Z1_LEFT_K11_O8#11:  - Z_k11_o8 <= 10
 Z1_RIGHT_K11_O8#12: Z_k11_o8 <= 0
 Z1_LEFT_K12_O7#13:  - Z_k12_o7 <= 10
 Z1_RIGHT_K12_O7#14: Z_k12_o7 <= 0
 Z1_LEFT_K12_O8#15:  - Z_k12_o8 <= 10
 Z1_RIGHT_K12_O8#16: Z_k12_o8 <= 0
 Z2_K10_O7#17:       - 10 sigma_k10_o7 - Z_k10_o7 <= 0
 Z2_K10_O8#18:       - 10 sigma_k10_o8 - Z_k10_o8 <= 0
 Z2_K11_O7#19:       - 10 sigma_k11_o7 - Z_k11_o7 <= 0
 Z2_K11_O8#20:       - 10 sigma_k11_o8 - Z_k11_o8 <= 0
 Z2_K12_O7#21:       - 10 sigma_k12_o7 - Z_k12_o7 <= 0
 Z2_K12_O8#22:       - 10 sigma_k12_o8 - Z_k12_o8 <= 0
 Z3_LEFT_K10_O7#23:  - Z_k10_o7 + d <= 0
 Z3_RIGHT_K10_O7#24: 10 sigma_k10_o7 - Z_k10_o7 + d <= 10
 Z3_LEFT_K10_O8#25:  - Z_k10_o8 + d <= 0
 Z3_RIGHT_K10_O8#26: 10 sigma_k10_o8 - Z_k10_o8 + d <= 10
 Z3_LEFT_K11_O7#27:  - Z_k11_o7 + d <= 0
 Z3_RIGHT_K11_O7#28: 10 sigma_k11_o7 - Z_k11_o7 + d <= 10
 Z3_LEFT_K11_O8#29:  - Z_k11_o8 + d <= 0
 Z3_RIGHT_K11_O8#30: 10 sigma_k11_o8 - Z_k11_o8 + d <= 10
 Z3_LEFT_K12_O7#31:  - Z_k12_o7 + d <= 0
 Z3_RIGHT_K12_O7#32: 10 sigma_k12_o7 - Z_k12_o7 + d <= 10
 Z3_LEFT_K12_O8#33:  - Z_k12_o8 + d <= 0
 Z3_RIGHT_K12_O8#34: 10 sigma_k12_o8 - Z_k12_o8 + d <= 10
 ZERO_K10_O7#35:     sigma_k10_o7  = 0
 ZERO_K10_O8#36:     sigma_k10_o8  = 0
Bounds
 0 <= sigma_k10_o7 <= 1
 0 <= sigma_k10_o8 <= 1
 0 <= sigma_k11_o7 <= 1
 0 <= sigma_k11_o8 <= 1
 0 <= sigma_k12_o7 <= 1
 0 <= sigma_k12_o8 <= 1
-100000 <= Z_k10_o7 <= 100000
-100000 <= Z_k10_o8 <= 100000
-100000 <= Z_k11_o7 <= 100000
-100000 <= Z_k11_o8 <= 100000
-100000 <= Z_k12_o7 <= 100000
-100000 <= Z_k12_o8 <= 100000
-10 <= d <= 0
Generals
 sigma_k10_o7  sigma_k10_o8  sigma_k11_o7  sigma_k11_o8  sigma_k12_o7 
 sigma_k12_o8 
End
