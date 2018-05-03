\ENCODING=ISO-8859-1
\Problem name: DECEPTION

Maximize
 obj: d
Subject To
 UTILITY_TF_3#0:    10 sigma_k1_o3 + 7 sigma_k2_o3 + 3 sigma_k3_o3
                    + 10 sigma_k4_o3 + 10 sigma_k5_o3 + Z_k1_o3 + Z_k2_o3
                    + Z_k3_o3 + Z_k4_o3 + Z_k5_o3 <= 0
 MIN_AS_o3#1:       sigma_k1_o3 + sigma_k2_o3 + sigma_k3_o3 + sigma_k4_o3
                    + sigma_k5_o3 >= 1
 UTILITY_OF_1#2:    - 6.25 sigma_k1_o2 - 5.6 sigma_k1_o3 >= -5.6
 UTILITY_OF_2#3:    - 5 sigma_k2_o1 - 6.25 sigma_k2_o2 - 5.6 sigma_k2_o3
                    >= -5.6
 UTILITY_OF_3#4:    - 6.25 sigma_k3_o2 - 5.6 sigma_k3_o3 >= -5.6
 UTILITY_OF_4#5:    - 6.25 sigma_k4_o2 - 5.6 sigma_k4_o3 >= -5.6
 UTILITY_OF_5#6:    - 6.25 sigma_k5_o2 - 5.6 sigma_k5_o3 >= -5.6
 _EQ_K1#7:          sigma_k1_o1 + sigma_k1_o2 + sigma_k1_o3  = 1
 _EQ_K2#8:          sigma_k2_o1 + sigma_k2_o2 + sigma_k2_o3  = 1
 _EQ_K3#9:          sigma_k3_o1 + sigma_k3_o2 + sigma_k3_o3  = 1
 _EQ_K4#10:         sigma_k4_o1 + sigma_k4_o2 + sigma_k4_o3  = 1
 _EQ_K5#11:         sigma_k5_o1 + sigma_k5_o2 + sigma_k5_o3  = 1
 Z1_LEFT_K1_O3#12:  - Z_k1_o3 <= 10
 Z1_RIGHT_K1_O3#13: Z_k1_o3 <= 0
 Z1_LEFT_K2_O3#14:  - Z_k2_o3 <= 10
 Z1_RIGHT_K2_O3#15: Z_k2_o3 <= 0
 Z1_LEFT_K3_O3#16:  - Z_k3_o3 <= 10
 Z1_RIGHT_K3_O3#17: Z_k3_o3 <= 0
 Z1_LEFT_K4_O3#18:  - Z_k4_o3 <= 10
 Z1_RIGHT_K4_O3#19: Z_k4_o3 <= 0
 Z1_LEFT_K5_O3#20:  - Z_k5_o3 <= 10
 Z1_RIGHT_K5_O3#21: Z_k5_o3 <= 0
 Z2_K1_O3#22:       - 10 sigma_k1_o3 - Z_k1_o3 <= 0
 Z2_K2_O3#23:       - 10 sigma_k2_o3 - Z_k2_o3 <= 0
 Z2_K3_O3#24:       - 10 sigma_k3_o3 - Z_k3_o3 <= 0
 Z2_K4_O3#25:       - 10 sigma_k4_o3 - Z_k4_o3 <= 0
 Z2_K5_O3#26:       - 10 sigma_k5_o3 - Z_k5_o3 <= 0
 Z3_LEFT_K1_O3#27:  - Z_k1_o3 + d <= 0
 Z3_RIGHT_K1_O3#28: - 10 sigma_k1_o3 + Z_k1_o3 - d <= 10
 Z3_LEFT_K2_O3#29:  - Z_k2_o3 + d <= 0
 Z3_RIGHT_K2_O3#30: - 10 sigma_k2_o3 + Z_k2_o3 - d <= 10
 Z3_LEFT_K3_O3#31:  - Z_k3_o3 + d <= 0
 Z3_RIGHT_K3_O3#32: - 10 sigma_k3_o3 + Z_k3_o3 - d <= 10
 Z3_LEFT_K4_O3#33:  - Z_k4_o3 + d <= 0
 Z3_RIGHT_K4_O3#34: - 10 sigma_k4_o3 + Z_k4_o3 - d <= 10
 Z3_LEFT_K5_O3#35:  - Z_k5_o3 + d <= 0
 Z3_RIGHT_K5_O3#36: - 10 sigma_k5_o3 + Z_k5_o3 - d <= 10
 ZERO_K1_O1#37:     sigma_k1_o1  = 0
 ZERO_K3_O1#38:     sigma_k3_o1  = 0
 ZERO_K4_O1#39:     sigma_k4_o1  = 0
 ZERO_K5_O1#40:     sigma_k5_o1  = 0
 GP_K1#41:          sigma_k1_o3  = 1
 GP_K3#42:          sigma_k3_o3  = 1
 GP_K4#43:          sigma_k4_o3  = 1
 GP_K5#44:          sigma_k5_o3  = 1
 BUDGET_CONST#45:   33 sigma_k1_o2 + 3 sigma_k1_o3 + 98 sigma_k2_o1
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
-100000 <= Z_k1_o3 <= 100000
-100000 <= Z_k2_o3 <= 100000
-100000 <= Z_k3_o3 <= 100000
-100000 <= Z_k4_o3 <= 100000
-100000 <= Z_k5_o3 <= 100000
-10 <= d <= 0
Generals
 sigma_k1_o1  sigma_k1_o2  sigma_k1_o3  sigma_k2_o1  sigma_k2_o2  sigma_k2_o3 
 sigma_k3_o1  sigma_k3_o2  sigma_k3_o3  sigma_k4_o1  sigma_k4_o2  sigma_k4_o3 
 sigma_k5_o1  sigma_k5_o2  sigma_k5_o3 
End
