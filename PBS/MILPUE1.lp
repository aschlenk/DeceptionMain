\ENCODING=ISO-8859-1
\Problem name: DECEPTION

Maximize
 obj: d
Subject To
 UTILITY_TF_1#0:    10 sigma_k1_o1 + 6 sigma_k2_o1 + 6 sigma_k3_o1
                    + 10 sigma_k4_o1 + 10 sigma_k5_o1 + Z_k1_o1 + Z_k2_o1
                    + Z_k3_o1 + Z_k4_o1 + Z_k5_o1 <= 0
 UTILITY_OF_1#1:    - 6.66666666666667 sigma_k1_o1 - 6.25 sigma_k1_o3
                    >= -6.66666666666667
 UTILITY_OF_2#2:    - 6 sigma_k2_o2 >= -6.66666666666667
 UTILITY_OF_3#3:    - 6 sigma_k3_o2 >= -6.66666666666667
 UTILITY_OF_4#4:    - 6.66666666666667 sigma_k4_o1 - 6.25 sigma_k4_o3
                    >= -6.66666666666667
 UTILITY_OF_5#5:    - 6.66666666666667 sigma_k5_o1 - 6.25 sigma_k5_o3
                    >= -6.66666666666667
 _EQ_K1#6:          sigma_k1_o1 + sigma_k1_o2 + sigma_k1_o3  = 1
 _EQ_K2#7:          sigma_k2_o1 + sigma_k2_o2 + sigma_k2_o3  = 1
 _EQ_K3#8:          sigma_k3_o1 + sigma_k3_o2 + sigma_k3_o3  = 1
 _EQ_K4#9:          sigma_k4_o1 + sigma_k4_o2 + sigma_k4_o3  = 1
 _EQ_K5#10:         sigma_k5_o1 + sigma_k5_o2 + sigma_k5_o3  = 1
 Z1_LEFT_K1_O1#11:  - Z_k1_o1 <= 10
 Z1_RIGHT_K1_O1#12: Z_k1_o1 <= 0
 Z1_LEFT_K2_O1#13:  - Z_k2_o1 <= 10
 Z1_RIGHT_K2_O1#14: Z_k2_o1 <= 0
 Z1_LEFT_K3_O1#15:  - Z_k3_o1 <= 10
 Z1_RIGHT_K3_O1#16: Z_k3_o1 <= 0
 Z1_LEFT_K4_O1#17:  - Z_k4_o1 <= 10
 Z1_RIGHT_K4_O1#18: Z_k4_o1 <= 0
 Z1_LEFT_K5_O1#19:  - Z_k5_o1 <= 10
 Z1_RIGHT_K5_O1#20: Z_k5_o1 <= 0
 Z2_K1_O1#21:       - 10 sigma_k1_o1 - Z_k1_o1 <= 0
 Z2_K2_O1#22:       - 10 sigma_k2_o1 - Z_k2_o1 <= 0
 Z2_K3_O1#23:       - 10 sigma_k3_o1 - Z_k3_o1 <= 0
 Z2_K4_O1#24:       - 10 sigma_k4_o1 - Z_k4_o1 <= 0
 Z2_K5_O1#25:       - 10 sigma_k5_o1 - Z_k5_o1 <= 0
 Z3_LEFT_K1_O1#26:  - Z_k1_o1 + d <= 0
 Z3_RIGHT_K1_O1#27: - 10 sigma_k1_o1 + Z_k1_o1 - d <= 10
 Z3_LEFT_K2_O1#28:  - Z_k2_o1 + d <= 0
 Z3_RIGHT_K2_O1#29: - 10 sigma_k2_o1 + Z_k2_o1 - d <= 10
 Z3_LEFT_K3_O1#30:  - Z_k3_o1 + d <= 0
 Z3_RIGHT_K3_O1#31: - 10 sigma_k3_o1 + Z_k3_o1 - d <= 10
 Z3_LEFT_K4_O1#32:  - Z_k4_o1 + d <= 0
 Z3_RIGHT_K4_O1#33: - 10 sigma_k4_o1 + Z_k4_o1 - d <= 10
 Z3_LEFT_K5_O1#34:  - Z_k5_o1 + d <= 0
 Z3_RIGHT_K5_O1#35: - 10 sigma_k5_o1 + Z_k5_o1 - d <= 10
 ZERO_K1_O2#36:     sigma_k1_o2  = 0
 ZERO_K2_O1#37:     sigma_k2_o1  = 0
 ZERO_K2_O3#38:     sigma_k2_o3  = 0
 ZERO_K3_O1#39:     sigma_k3_o1  = 0
 ZERO_K3_O3#40:     sigma_k3_o3  = 0
 ZERO_K4_O2#41:     sigma_k4_o2  = 0
 ZERO_K5_O2#42:     sigma_k5_o2  = 0
 BUDGET_CONST#43:   57 sigma_k1_o1 + 77 sigma_k1_o3 + 68 sigma_k2_o2
                    + 68 sigma_k3_o2 + 57 sigma_k4_o1 + 77 sigma_k4_o3
                    + 57 sigma_k5_o1 + 77 sigma_k5_o3 <= 363
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
-100000 <= Z_k1_o1 <= 100000
-100000 <= Z_k2_o1 <= 100000
-100000 <= Z_k3_o1 <= 100000
-100000 <= Z_k4_o1 <= 100000
-100000 <= Z_k5_o1 <= 100000
-10 <= d <= 0
Generals
 sigma_k1_o1  sigma_k1_o2  sigma_k1_o3  sigma_k2_o1  sigma_k2_o2  sigma_k2_o3 
 sigma_k3_o1  sigma_k3_o2  sigma_k3_o3  sigma_k4_o1  sigma_k4_o2  sigma_k4_o3 
 sigma_k5_o1  sigma_k5_o2  sigma_k5_o3 
End
