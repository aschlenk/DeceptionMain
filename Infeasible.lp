\ENCODING=ISO-8859-1
\Problem name: DECEPTION

Maximize
 obj: d
Subject To
 UTILITY_TF_1#0: 5 sigma_k1_o1 + 7 sigma_k2_o1 + 5 sigma_k3_o1 + 4 sigma_k4_o1
                 + 5 sigma_k5_o1 + 2 d <= 0
 UTILITY_TF_2#1: 5 sigma_k1_o2 + 7 sigma_k2_o2 + 5 sigma_k3_o2 + 4 sigma_k4_o2
                 + 5 sigma_k5_o2 + d <= 0
 UTILITY_TF_3#2: 5 sigma_k1_o3 + 7 sigma_k2_o3 + 5 sigma_k3_o3 + 4 sigma_k4_o3
                 + 5 sigma_k5_o3 + 2 d <= 0
 _EQ_K1#3:       sigma_k1_o1 + sigma_k1_o2 + sigma_k1_o3  = 1
 _EQ_K2#4:       sigma_k2_o1 + sigma_k2_o2 + sigma_k2_o3  = 1
 _EQ_K3#5:       sigma_k3_o1 + sigma_k3_o2 + sigma_k3_o3  = 1
 _EQ_K4#6:       sigma_k4_o1 + sigma_k4_o2 + sigma_k4_o3  = 1
 _EQ_K5#7:       sigma_k5_o1 + sigma_k5_o2 + sigma_k5_o3  = 1
 _EQUAL_o2#8:    sigma_k1_o2 + sigma_k2_o2 + sigma_k3_o2 + sigma_k4_o2
                 + sigma_k5_o2  = 1
 _EQUAL_o1#9:    sigma_k1_o1 + sigma_k2_o1 + sigma_k3_o1 + sigma_k4_o1
                 + sigma_k5_o1  = 2
 _EQUAL_o3#10:   sigma_k1_o3 + sigma_k2_o3 + sigma_k3_o3 + sigma_k4_o3
                 + sigma_k5_o3  = 2
 ZERO_K1_O2#11:  sigma_k1_o2  = 0
 ZERO_K1_O3#12:  sigma_k1_o3  = 0
 ZERO_K3_O2#13:  sigma_k3_o2  = 0
 ZERO_K3_O3#14:  sigma_k3_o3  = 0
 ZERO_K4_O1#15:  sigma_k4_o1  = 0
 ZERO_K4_O2#16:  sigma_k4_o2  = 0
 ZERO_K5_O2#17:  sigma_k5_o2  = 0
 ZERO_K5_O3#18:  sigma_k5_o3  = 0
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
-7 <= d <= 0
Generals
 sigma_k1_o1  sigma_k1_o2  sigma_k1_o3  sigma_k2_o1  sigma_k2_o2  sigma_k2_o3 
 sigma_k3_o1  sigma_k3_o2  sigma_k3_o3  sigma_k4_o1  sigma_k4_o2  sigma_k4_o3 
 sigma_k5_o1  sigma_k5_o2  sigma_k5_o3 
End
