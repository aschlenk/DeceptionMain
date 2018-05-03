\ENCODING=ISO-8859-1
\Problem name: DECEPTION

Maximize
 obj: - 4 sigma_k1_o1 - 5 sigma_k2_o1 - sigma_k3_o1 - 5 sigma_k4_o1
      - 5 sigma_k5_o1 + 0 d
Subject To
 UTILITY_OF_1#0:  - 2.4 sigma_k1_o1 - 4.5 sigma_k1_o2 <= -2.4
 UTILITY_OF_2#1:  - 2.4 sigma_k2_o1 - 4.5 sigma_k2_o2 <= -2.4
 UTILITY_OF_3#2:  - 2.4 sigma_k3_o1 <= -2.4
 UTILITY_OF_4#3:  - 2.4 sigma_k4_o1 - 4.5 sigma_k4_o2 <= -2.4
 UTILITY_OF_5#4:  - 2.4 sigma_k5_o1 - 4.5 sigma_k5_o2 <= -2.4
 _EQ_K1#5:        sigma_k1_o1 + sigma_k1_o2  = 1
 _EQ_K2#6:        sigma_k2_o1 + sigma_k2_o2  = 1
 _EQ_K3#7:        sigma_k3_o1 + sigma_k3_o2  = 1
 _EQ_K4#8:        sigma_k4_o1 + sigma_k4_o2  = 1
 _EQ_K5#9:        sigma_k5_o1 + sigma_k5_o2  = 1
 ZERO_K3_O2#10:   sigma_k3_o2  = 0
 BUDGET_CONST#11: 4 sigma_k1_o1 + 25 sigma_k1_o2 + 41 sigma_k2_o1
                  + 79 sigma_k2_o2 + 25 sigma_k3_o1 + 41 sigma_k4_o1
                  + 79 sigma_k4_o2 + 41 sigma_k5_o1 + 79 sigma_k5_o2 <= 277
Bounds
 0 <= sigma_k1_o1 <= 1
 0 <= sigma_k1_o2 <= 1
 0 <= sigma_k2_o1 <= 1
 0 <= sigma_k2_o2 <= 1
 0 <= sigma_k3_o1 <= 1
 0 <= sigma_k3_o2 <= 1
 0 <= sigma_k4_o1 <= 1
 0 <= sigma_k4_o2 <= 1
 0 <= sigma_k5_o1 <= 1
 0 <= sigma_k5_o2 <= 1
-5 <= d <= 0
Generals
 sigma_k1_o1  sigma_k1_o2  sigma_k2_o1  sigma_k2_o2  sigma_k3_o1  sigma_k3_o2 
 sigma_k4_o1  sigma_k4_o2  sigma_k5_o1  sigma_k5_o2 
End
