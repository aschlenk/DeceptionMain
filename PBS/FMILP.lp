\ENCODING=ISO-8859-1
\Problem name: FEASIBLE_MILP

Maximize
 obj: x16
Subject To
 UTILITY_TF_1#0:  3.9995361328125 sigma_k1_o1 - 2.0004638671875 sigma_k2_o1
                  - 0.000463867187500355 sigma_k3_o1
                  - 2.0004638671875 sigma_k4_o1 - 1.0004638671875 sigma_k5_o1
                  <= 0
 UTILITY_TF_2#1:  3.9995361328125 sigma_k1_o2 - 2.0004638671875 sigma_k2_o2
                  - 0.000463867187500355 sigma_k3_o2
                  - 2.0004638671875 sigma_k4_o2 - 1.0004638671875 sigma_k5_o2
                  <= 0
 UTILITY_TF_3#2:  3.9995361328125 sigma_k1_o3 - 2.0004638671875 sigma_k2_o3
                  - 0.000463867187500355 sigma_k3_o3
                  - 2.0004638671875 sigma_k4_o3 - 1.0004638671875 sigma_k5_o3
                  <= 0
 _EQ_K1#3:        sigma_k1_o1 + sigma_k1_o2 + sigma_k1_o3  = 1
 _EQ_K2#4:        sigma_k2_o1 + sigma_k2_o2 + sigma_k2_o3  = 1
 _EQ_K3#5:        sigma_k3_o1 + sigma_k3_o2 + sigma_k3_o3  = 1
 _EQ_K4#6:        sigma_k4_o1 + sigma_k4_o2 + sigma_k4_o3  = 1
 _EQ_K5#7:        sigma_k5_o1 + sigma_k5_o2 + sigma_k5_o3  = 1
 ZERO_K1_O2#8:    sigma_k1_o2  = 0
 ZERO_K2_O3#9:    sigma_k2_o3  = 0
 ZERO_K3_O2#10:   sigma_k3_o2  = 0
 ZERO_K3_O3#11:   sigma_k3_o3  = 0
 ZERO_K4_O3#12:   sigma_k4_o3  = 0
 ZERO_K5_O3#13:   sigma_k5_o3  = 0
 BUDGET_CONST#14: sigma_k1_o1 + 40 sigma_k1_o3 + 76 sigma_k2_o1
                  + 61 sigma_k2_o2 + 17 sigma_k3_o1 + 76 sigma_k4_o1
                  + 61 sigma_k4_o2 + 52 sigma_k5_o1 + 30 sigma_k5_o2 <= 214
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
      x16 = 1
Generals
 sigma_k1_o1  sigma_k1_o2  sigma_k1_o3  sigma_k2_o1  sigma_k2_o2  sigma_k2_o3 
 sigma_k3_o1  sigma_k3_o2  sigma_k3_o3  sigma_k4_o1  sigma_k4_o2  sigma_k4_o3 
 sigma_k5_o1  sigma_k5_o2  sigma_k5_o3 
End
