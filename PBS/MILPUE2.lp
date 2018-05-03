\ENCODING=ISO-8859-1
\Problem name: DECEPTION

Maximize
 obj: d
Subject To
 UTILITY_TF_2#0:    10 sigma_k1_o2 + 7 sigma_k2_o2 + 3 sigma_k3_o2
                    + 10 sigma_k4_o2 + 10 sigma_k5_o2 + Z_k1_o2 + Z_k2_o2
                    + Z_k3_o2 + Z_k4_o2 + Z_k5_o2 <= 0
 MIN_AS_o2#1:       sigma_k1_o2 + sigma_k2_o2 + sigma_k3_o2 + sigma_k4_o2
                    + sigma_k5_o2 >= 1
 UTILITY_OF_1#2:    - 6.25 sigma_k1_o2 - 5.6 sigma_k1_o3 >= -6.25
 UTILITY_OF_2#3:    - 5 sigma_k2_o1 - 6.25 sigma_k2_o2 - 5.6 sigma_k2_o3
                    >= -6.25
 UTILITY_OF_3#4:    - 6.25 sigma_k3_o2 - 5.6 sigma_k3_o3 >= -6.25
 UTILITY_OF_4#5:    - 6.25 sigma_k4_o2 - 5.6 sigma_k4_o3 >= -6.25
 UTILITY_OF_5#6:    - 6.25 sigma_k5_o2 - 5.6 sigma_k5_o3 >= -6.25
 _EQ_K1#7:          sigma_k1_o1 + sigma_k1_o2 + sigma_k1_o3  = 1
 _EQ_K2#8:          sigma_k2_o1 + sigma_k2_o2 + sigma_k2_o3  = 1
 _EQ_K3#9:          sigma_k3_o1 + sigma_k3_o2 + sigma_k3_o3  = 1
 _EQ_K4#10:         sigma_k4_o1 + sigma_k4_o2 + sigma_k4_o3  = 1
 _EQ_K5#11:         sigma_k5_o1 + sigma_k5_o2 + sigma_k5_o3  = 1
 Z1_LEFT_K1_O2#12:  - Z_k1_o2 <= 10
 Z1_RIGHT_K1_O2#13: Z_k1_o2 <= 0
 Z1_LEFT_K2_O2#14:  - Z_k2_o2 <= 10
 Z1_RIGHT_K2_O2#15: Z_k2_o2 <= 0
 Z1_LEFT_K3_O2#16:  - Z_k3_o2 <= 10
 Z1_RIGHT_K3_O2#17: Z_k3_o2 <= 0
 Z1_LEFT_K4_O2#18:  - Z_k4_o2 <= 10
 Z1_RIGHT_K4_O2#19: Z_k4_o2 <= 0
 Z1_LEFT_K5_O2#20:  - Z_k5_o2 <= 10
 Z1_RIGHT_K5_O2#21: Z_k5_o2 <= 0
 Z2_K1_O2#22:       - 10 sigma_k1_o2 - Z_k1_o2 <= 0
 Z2_K2_O2#23:       - 10 sigma_k2_o2 - Z_k2_o2 <= 0
 Z2_K3_O2#24:       - 10 sigma_k3_o2 - Z_k3_o2 <= 0
 Z2_K4_O2#25:       - 10 sigma_k4_o2 - Z_k4_o2 <= 0
 Z2_K5_O2#26:       - 10 sigma_k5_o2 - Z_k5_o2 <= 0
 Z3_LEFT_K1_O2#27:  - Z_k1_o2 + d <= 0
 Z3_RIGHT_K1_O2#28: - 10 sigma_k1_o2 + Z_k1_o2 - d <= 10
 Z3_LEFT_K2_O2#29:  - Z_k2_o2 + d <= 0
 Z3_RIGHT_K2_O2#30: - 10 sigma_k2_o2 + Z_k2_o2 - d <= 10
 Z3_LEFT_K3_O2#31:  - Z_k3_o2 + d <= 0
 Z3_RIGHT_K3_O2#32: - 10 sigma_k3_o2 + Z_k3_o2 - d <= 10
 Z3_LEFT_K4_O2#33:  - Z_k4_o2 + d <= 0
 Z3_RIGHT_K4_O2#34: - 10 sigma_k4_o2 + Z_k4_o2 - d <= 10
 Z3_LEFT_K5_O2#35:  - Z_k5_o2 + d <= 0
 Z3_RIGHT_K5_O2#36: - 10 sigma_k5_o2 + Z_k5_o2 - d <= 10
 ZERO_K1_O1#37:     sigma_k1_o1  = 0
 ZERO_K3_O1#38:     sigma_k3_o1  = 0
 ZERO_K4_O1#39:     sigma_k4_o1  = 0
 ZERO_K5_O1#40:     sigma_k5_o1  = 0
 BUDGET_CONST#41:   33 sigma_k1_o2 + 3 sigma_k1_o3 + 98 sigma_k2_o1
                    + 16 sigma_k2_o2 + 86 sigma_k2_o3 + 54 sigma_k3_o2
                    + 55 sigma_k3_o3 + 33 sigma_k4_o2 + 3 sigma_k4_o3
                    + 33 sigma_k5_o2 + 3 sigma_k5_o3 <= 180
Bounds
 0 <= sigma_k1_o1 <= 1
 0 <= sigma_k1_o2 <= 1
 0 <= sigma_k1_o3 <= 1
 0 <= sigma_k2_o1 <= 1
 0 <= sigma_k2_o2 <= 1
 0 <= sigma_k2_o3 <= 1
 0 <= sigma_k3_o1 <= 1
 0 <= sigma_k3_o2 <= 1
 0 <= sigma_k3_o3 <= 1
 0 <= sigma_k4_o1 <= 1
 0 <= sigma_k4_o2 <= 1
 0 <= sigma_k4_o3 <= 1
 0 <= sigma_k5_o1 <= 1
 0 <= sigma_k5_o2 <= 1
 0 <= sigma_k5_o3 <= 1
-100000 <= Z_k1_o2 <= 100000
-100000 <= Z_k2_o2 <= 100000
-100000 <= Z_k3_o2 <= 100000
-100000 <= Z_k4_o2 <= 100000
-100000 <= Z_k5_o2 <= 100000
-10 <= d <= 0
Generals
 sigma_k1_o1  sigma_k1_o2  sigma_k1_o3  sigma_k2_o1  sigma_k2_o2  sigma_k2_o3 
 sigma_k3_o1  sigma_k3_o2  sigma_k3_o3  sigma_k4_o1  sigma_k4_o2  sigma_k4_o3 
 sigma_k5_o1  sigma_k5_o2  sigma_k5_o3 
End
