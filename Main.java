import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.*;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String payload = "{\"hdrs\":{\"nm\":\"FETCH_APP_DATA_REQ\",\"ref_id\":\"2E2AE615B63C464CB8492931AF1D76AA\",\"tms\":\"2022-10-31T01:24:03+06:00\",\"ver\":\"v1.2.2\"}}";
        String srlzd = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0..CTzYDEv_NWxJ6ixr.kgfTZ1x2_j8jqdnhSzXGjLL5lgH5sc1okIUYSS5tLFFVfu5_TryIrHQuOB34tAfnEzDtcMVn1CnwHVqtPd2LSMvdmcSmL1GmQJuRt-a9qX6TkuPg2v5aIhjWlP7yJ864F2H-93GTG-urBMFiHOXhw7e6uU6ldc7C4RgFZNVuEsCcBmb6dtlqBc26eXvD0KNUFj7_cajchZdnBKOITSdT0vlcPCMs4tPqoIcHkB9_h1skVxMmOhwdMqKor_w_lAR0PwYnJm2S8uvGlqNtPLw1UsvmCskMwEz1Tw9LBJLSUHirD4AVfrmhZK4lXLQL4bJkZW439AE08UZLiZfE_bPccAki3Lpj_k5s8yxZaTw9xlee8fmpS6I29l3Tnsx2MNEDbeLZNPgL5u-vWDK-Ag5R7gk-fWc25I_Cnu6hH1AQZ-a1PcL8JUfs_CKZCAKKknK4XzcOtMnwpcR0Qo_9O4XWFgHhchJPy52aoLXx_6fqRAesGum_tIjdlQ5gYKZLtgjKPcPaewLZb20AlhWIsC_B_OBTIPZ4kpo6HUaz3q6AzSx3R3c1I8Kk9dbvtT7b1MplGW6wrbSNeeYQljOGYptudSRMxdjMEeDLb0_jKgvFSPs2c3KpEr5O22spyDJ6QjNIIFDmMBn2f1mShYmBsTo0gV-DsIhYv4_3h1dWezLLWXNcaIya7vGUQlVkq3rmKnZg1RFKncdgVnw6k6zfBPyCZMIOdb2wjzUbDqJbz2zhiibPuW0Ve04SEfP0KRJv0CHToPfjCngJQIWZeZzRsn4Ga1V4Rcow5-pR_F_T-t7Ahy--nvgdZcNq05csQLgrndzspQtUmxDhoPfEZ_HfZyhFqDC-zA0Ho9yJkAr2LHLIdn4UxXdkkhlSxKHcVfgtZvYOT717-4U_QjfndbpU2iG9GdLbvyOLcojPGqOafEMc6gqvh59MZiXvlQRqTKbqvQ-vqkmZ30X7b3f6VyfXv4nWRSg7L54zZ5_Ol_gku5ohsW-WQg6z6QQof5IcDfrAHJ5ULpqsN1bou_sS_BlM9LMKoS_p1yU1eF8g27jHNjbL2Xy7ZO5QMTO--aPB1JLWC1jVtYwjP6KlLE4jSpKRdMKGMlj2C3ALO3LV5Zc5zhblxHMts-mjC4KHl_Bh07bp7qawtFzPP7DW7Dma129_nxlZAdxc_8G5VgR7wLceOD7C3-eq0Ga1UkROMMQcwQSZRhPkCv-P7oEm3olguMD3-k87Xh8NGo4fuROTfXQIj0UDIPeB5Jo822MTNM28ePUYnCPBg_MHgYBoLDOpcNKVyoNITxs0SYrbjvCWaYU_SPgQ6dfZJBhnfsATMReM8oBSht1jOqXeF9lPrRqAXJDsBbQDn9aeHPC10RTcffWLzRxxsNiCefxUhEurs4-RBWmprD9d-Jv11LD4jgkd9RtsVZ2isoKKzd0q8rWDy029Ss60PPaI1RKXixuX1Zq26ssIwVsF44WbsKRZD5s1iB3QHLp7H7S06g4Plkg612GSZSDf2xeMyrOYI9GhzMlIh3NwbYsb9N82lAWNg-SDDXVR4_O2hxXM8ZrsD9ix9V7YdonAvFyi7mGGa1-lIpzvYqVBH7HMpISkVIbUwxrbD6Ach8QVS11N1-CAK3qhuDnx22CIK2PNpG2p4USCui1dglFGPaQkszStLnoTVIpmwysgLJpyMC4GFhqrjUcNE79gM23Tnkd7STvxYHdvwLQXRMv3UbmGlsX7-rqPrsn2Ay_oJv2dbYAsk_hu-MIplz7RM5B2bWY_MEsrkyFfUHrwWsaTigBpo36Zl60r8idwt2olR-7Dk0KAmxGd9JLcmQ4XeOZEf_u-5nCmf2SRXOAOKP0RdPGVxfhxB7fXguHdSUXFLYLQFJlNEtyD2OlFd7tV-iswkAdGpekSkL8AQR3J2LvXy7hx0Ie7KGk1ORdwxKF8wvDlNL7GmO1trR3o3SeQMJbeKGvfD9GhENJRAxCtQS20JVfkdaLmfgKPR5JJ8dRCcTweHbNI3pEIHZsY4pC0WHGyJI1klNWAVMBRONnM0EWfnyJAHhNBMYiUht7f7FwpLpqAPuOxZVFEz-a-0Kvk_l6p7abZQZb4O3CwrsEvXUyn6rdesw1JEFlIKArIzSk4lwGUT-jh3O5V-i40GEmd0lC3lbk-eUM4_y3LZGZqpbmA6Z-Sg0TpQ6zOpT6AwGYPhTXiwo6l1jttvsdr0NUQYe-Hj9Y29h7h9ifIuKpBoKRxjrgPNDGUC4xxpYXbHeBTeOTH8fQNy3VBtoF8cSU7QEzY6s0B5KBpR7uawZ3Y9pY_vlfxnTXmKEWMrB01gHAqtk-DcwfgZHmb90s-md3z5mPjuwodQB1poUKUb9DrA7ZEA7hM8s26ioTTC4w9jB_XPVFF58A08lPks03-JmMG9PhnPEtlYuo8yNTNfj5S5DnF1UN48_X5svF-jIpFdWefHwxF4rsLW1boBikjjVx50UEDwICfNN4UqmgVa6PjekgedKHaAXb1GgbUJwnNdjM3QPajLJDKhvL_3_N_no0yUiY1yVCnT4RzSMuRl6XRLyk9SZA_kjzKTb0u6KATFMK8OnpLC2NnawD_NOo5CmrtW6dS2mlMx5-y3BEyKMLCaXrsr3UEV9Ehl7f_56hJkcccGRRfamC5D0h687c_pJserp4DpbENnwjeWbp7gpF0c1qCg5t8FI0YBAygTUGhzvibp9fD32wTeAuFjsmtXBWaU3cEgxkG2-hTmGKmr-kDtbmc691EyKdgh5-k30T_8xNeIpnBLV92W7ciMJuFPbFmIi4bjt5DqUBOS4lQKkMHxolE4m0tT3lSLn1pM5a69hPp9K_zbWftk-SBcKeA2A33zAOIDmj-sDfvzAhUOozbzTEOmrtxQ-9YzuyuuBCtPL71fYWaT1IZBMOmeBMmHE-M7NKX0bJC4Woouyf8TVz0IdOzwnBBTTEIyer6KvV_wjutwKfRiyMQI049_mfk_G6Da54c3Ga78iO46w3eClo8pud5j93hrCQSliun7SMkPta4tEhlVT8G61-0TwBUkBj0JLpGNataAJJfs2zeSgZrx9TG9enkTLg5Abhm1TQrHXuZH8kC9FjGMKjfa49fjjtzPrWiSY3BM_Y4Wrb2DTspVZYtGyR9cZ-Z4q9JG2AwUIqp32Ij5gHtSkSMCLjEfCgTNtJP2UxajRxmY4aIPF7v-EZflpsZH2VoQ_clgv7axvYOeXfcm_BJyGdaURo_7YyXZAxpF_dbYDVC41KegJZhh6o6xWmpsCZTN4hrzXGCdKvalwmv1m_lubjH-804I2PQYjM8OTwdxaLj6aMkisO5d7RhVpnQlOCkIrVGCdIkFZ2-my2FrEDzjG2YLHFt8VCsmui9aMwsptU-s2y308iC6Q_Ix2af20BpTEtmvNk31lB4de6ahsWcunIcoosb1mKfpF9qsAtjn6rkIhdbF6_ZgqBrMVKB5upWz6hl1tpIElxPuIeojocs2ea4GE0ly_TCSCDh2JipJR8JJ0YmV-AV8NmZbSMA1SdXX6y0IsLJoFsYFmichBObu-qxYi5Lo_OtV7CDrHgX-y9zteoqtt24DRUBV3-HoPmtekApc2zicToSchIJHvd3LDgZ5HjHPKF2ZFtlsze9qZK8mzdon1hJIAuNo9D-kCr5N_6C_T-skgP-ZChZYDzuEfzAfGO0seBE1BozlXWSNUdvexI-nFm6uOPSuv751m2Lf7pYTe5C6t_4vopR4IzG7DAC0BTvpM7QdZZUAc7dynJp4-IEKtLg1-CUGQWI4pOoPbK6_U46ZxtB9bveD4RRfnILpPssgsBbQvZZ855FQYrhX3nqPwd7pUos0aRf3p6gF8q-fs0cN9ulGyA7QCbAgxpvxEep0mJpPvzfyXWwuLyVfypK81t90gt7Gg6231YQM1FJQE_i4mPjdloqWDCZPcHlCAk8L85zF_hxRrCKn1gg8VFi7Lp3rfvtNjS2-vWVjgPjbQkksHu1zFnBx0xhfhZyI5Hi2qJCMuGhQ5s5bwUzm7Cn3I2zZEL7jZJTFuE5HWehp0Grd7V8AO06IvvL9982jM7AGILByBgqGStF9fvOTTGkOjWqJ48BSew5lK5aMXMA170UTKC4_bJT8LB4hJjH96DBLuN9j34M2dWLrmvw-mhaukhhxGEx0PzJptjxKCT9qUQ_lK8_9C3A2ApgYr4s_PSM-FIwIVCsEk3wW_j3WYcVtK-FaEKza93eNtPAkgJK0D0po2GlZ5NQyuvIAlN0dLYvPu6PwI5-FmH0yE-3GmCo505C21tvFlruhTAr9CbxMY3JykVHAnYaNBBH6vbO0mtuDqOO2Fj3HxKAiq3ZJNb77DbJVJ07cjO8atshuyuItv-FJp5mcaJlCMuUuwHi4TtivdkIpffMMiTTY4UURn1B7gfh821ZlC_2bj-EC0kQx7314hsRkHUZgJ0OSzM-mtZI-FSZq0kCjtpB9F-c-0Jo0Ph95iPBJtLwervm9viNVM-90jvy4WsQ-SQLM9sR6kd9UJy5p37LACcCamUz7qgflH3sUgnjwP1JASvLwBerMm1TsyDc6vKFj04ZmPaBpwe5OZNRnbcjXJNPyBHtpcTxt2mGJ0BWiuZV8K_YPxYUqX-zOLXd_uA_iOKNRYGap5p5VUVyJNGIg-8e64duBOnNFvZLjXYVQgblMxE3ik58y8autlAfnTI8uoTfDTL0zFkS_JzYdDq1KOJoOuxStiF7vKOMyKcimk2IpApk7W6vdWlIqBprUxBhwTrfjcblpl2Y5fwWqRvS_ksYwiQol7grNHo47dCMT51NLiqAClHhsS0H3UZFLXCaCqiKl8iU5gpITY7c3eEMi_XUhgMNlb8mO-CuYLZ4M1hDUn38MycFXNamfYZumDlYl0PGXKrYceSDCpXhTvMHWzwnK2I71zZVWv1myrxPfhRRGxPdFDPPBoPW4XcGIWd-YZq7zfNSHRcbdyKdleRPq15R7Rrx4o7TGt4wV3WijVpEcyi2sNgd7obk_HjCw5Sai_IX4DvUq5LKPRXeqmB4GB_Phm2ZOQyCZ9rTaBhrh2zz89tkQilHiunJa9NzWMz9xjAidkA1zKW3gGci_owmUMjPTks5lEujBzxcEcOZSdwIDzSgN7CgTPAbYwBrkVQB0C6QWXNjV5428IDxPlxcmBKz0AUyh6b_TVf0go81ASWGijWvfz4aecYbcciLLOWIA72tkCjjWyGWaZXWvYdmZFNV4WT5QSEt1fOeSdF0RJsPKj93hr-ZHDgW5Sbw62MyKHkYx3cPIOx6uvFh9PGVPYrIWCOnbAyJZtVCV4o78GTHX9N85-FiHzI3nLdf4YpQdg0eofNVoSCHOTD9YLW52c55xEGISUkGujU-Gzm2i9DO86TtUb9aihlhvngv7bTxC_16EqmNcCk_-d77jlzdOS2tmq46edBAuZC1hn0qPDWaSO596pEDN8IHQN5lro81SVzEbGELh_f4eCZ6VE4A0PdEnkxtGub_4QZC8-7OXY7ayOEvMNfRBI9QYwclc-Fvg_9VxcutOxlCXU_ZH_r7vSbrdl2No2cHgCf8nQsLOLkZ-oeZ2ynaUI8yLUdLJDPoFWqSgel13U6lEWeKt-v-3T7xdfO1dvFSBE8l0JyUw7-UihzEZWCDtBoIm7bAar2d-m4aKl88JldCNmt1M-auv_BE7n8X_QeLt_XdD_C0cMwvLu1Oy8lWGHr43LgoruMMl7LmcC5bILR60FQk9PI2sCIUNzfemNLVgPl4UkjUele08Hl46ty5OMRRn8ifuJJ_SgygCPTnRFEkDzukoetlQwj57W5Kb4gqTtAPzsBoEz0hsnZNxd2R6kqQjK7pMBkLE38vwA9fIzPyG_SmwgtrlMnvZdZLMLEg_w93n-LNdWjxNFiGUqAX0mB2-szDlSUwwd8tsh9Z8zoYzyynilD7d3vCwNsKp9llnDRURnFlfbEd_li8kPLWsjiqBYwgWS6UB8CbwA80EAiORdik4H7DWUPRjAJWpPQt7_aD6-TNCLcbv-aMK02Tr1gQpN07waf0jSKm6Zb6YgCPa1indPqJbg6cNmwcmfHOXoOvFl0ruvjUq_FIBgau6gcdDfM62jPLT16_6wHVy_hGhYGyoaZhzeaUPDbX-4r5_HQKGYjP1APkLbxs8qZy2LNvxFf_fyD367nHO7fRRsX-d1tKM7-F75S5o1xbx84OTS531uhaUOqL8GNnsLkjsY-_lOdwc-It3NoQqILB7pnTl3G-gIEMPCYeD_2cbAwu4qlD2cS5DzdhwBfThDSRqPnXzEcQvilVC0l3BH4aVuXxS-MLE3plQQ2mVd84WYThkZ1Iq7KHp3u8NgCuDfmbFtIzKfYBT646AJ4zJ0Eeo0cTGrtAeuGb-_QUMAwP2JM8jbE121y0UvcVvaE5um6g5HBpYNaFc5Iq0BX4ZkLjxTe7ZYV93Rev4eYQyvpzbEQDZZz-Ayh-hEKPN1-emJ7IroVM0JI1zKRu6MdNMVqqgVrDAoS9uUtxDdguHTl-hgEkph0mJVbUSfVT9LwJGd584xXgbwsBerHjJUQ2gKMQLunySZ70yBGKgkSjWjRRjokcRSeMc90A--eY81gE9Vy9jNNSIT5nzZ_2og_ZfjhO_dCs4X-Tq2ZRGeyKVsn05DrRkcvY3f5m7f1uiCYzLrrzusYFbUYcK2LBrWLuMU5jS2ZgItfuLAhbXAbp7AJnY_ln6JqTw5UmuB4tGgldvDZAvo6OPaAjLLOpIP7eeJ_--0DdSGNDFqinUkrIDOxC5mfvlvxS8o-PAEoPKXwRMO2PeYHP6oK5TSfFrDTaljQusa5_sZxsifNY8mrUqTq67SmS9v4PpnvSoKSs9bWA_CsI4zK8KgyWcn6VHhtKKlPvFM_-zZUITNHge0DZ57KFMbDqhSDhzpHzXCY5lj9kVoxXZdPaH6ZWanvNyoSICdRCQNgO2q9FjpPQS-B3VhmV_TXKruxoCQo2yw8vcSiBr1SiENzdrhCA99Hiq-lYO-p7SZnij98aVEzHjCAWhfhuHf-y8vL0gRvd-ngdMeuPdyVo67R9J79zSutxpeuq81fjQRCQKz2H4EnBBsclgagC1-bUrzBZva8vJkDAP0zNMdDhAPuElO5Y6eratH9buYBOdr_aaUoHTvMyKXH1CRGQS9ctfCR_Z_4rRjLJcCZcC1i2xazESYEpyUVOEcxIrScw38KFRqQydjeHv43IuVImFOEYfMKs6g57yMpTVQLnHMzQaZrYHGKmNMGrTF_7xN-ci0_m__P5yejw-KRIyIB_BaVCc5LdikPTLkgft8owi4o8x8lrUTXSn7jmzeYBKrPw_CW97ZYcaVo377kefunqy5XlZmU4cUuEg6rBEzKr7yujFIa6WsB1OiHUV67Rm3JRTNz-78T_LrGqT2QBkxtIBFwod0a3QBcys_lWNGCE9A6PVr1MMSmaffYDaraTYFdnNmuR3isxNRtKRSkThe-LYXI9V9_dAftT6tKrzHf3fzZ4c5d8qAFxtj25Zr-ZbDO3cPJHwW-2uaAo_8L0BA4bxv0pLN7agbe09mDAP0Pwu10sJRXNPP85ePbs6kiNc3OULQEdjb6FjWfZguZGGIhw5W8sgXVM26w1iMOlpH1ICzd1xt_GAkQt1j7mc1w6adNOz29jiK5aWXqTEnMcGtLR0_kaJwA0t8FtLE3vRWviFnaPFafToVcDc9vtd3uxloC95U3ECSyG4v7THbXB177gIF315MUYF4XNCdVVTHMMq6Q5ZOSejHzCbWjG5E2qsd_C6NI_tUHDfrF9iAFIpB3p4iocDc6I6MbTZUc8CyTnUcYQez2deXUK-fnB6LtCYbpBdzcr279OYsGXjg5YyDafW1AvUIj7JpsYxDEgLSwIJS5AOUNvLds5-ZPwaGknrBxXdNfzeN1wBnUJqI67ATJ56dkYTnFOwnwD7FP_CvhvO-Hkcaoxv-wu_73pIm4UHG50xbHZzsr2OEt9OzkgeanltLiVnj8aLv0TxhNCrehnB9TwexkxHFOBFcQmpH7jB9A9NN0KG0zk4jLMHSeykkGLg14_BmEKiotibqZKI03JJErUTd6SL7MnE6LS6Es4HEGQD3Xz1p60gDv-ExMUdadhD4HmB6OgCidg99isfCXJwFvQoTxrQNzJOxwUwv6fW8YM9mMV0-SyEgkPapc9JhA-3UHW5L75dAYQMezDgNWdK1yJHmhaj9eBgrAeTmlmcq9R1sT9UZhroZKnVcu-nqzxnmHDwiCx5aqDuYACyAOF2DcfilPBU-5ru8J2hmbthlyvdziSjauYU6iW_OJ-Gfym6OUwpbWp87NaSub90ds4ozVERM0MBWMuvXCdAu4E0wcmC96evVeOzgasDToHqNOo99RY65nN_yatlpZepImQuJ5CC7-WT2etbZUpufmN0niv12xOURNRZwrch_sLk2AbuURl4dBEBYih-nMsYEdoRd-PfQCTCMcNdWCIgwxxogJtKRZwdBFQ_nVkIWUxI4IRA-isI5rYewTmYlr0ZmAGRSo13soEbdBZtVytjBI42NUWHLR7ouqKzFoh4YLnovgm7IN3hn8Ub7PUvltAaj9ggUKO3x3xL1-qAE_QigUiQ6kjLeMyDbNt8l8eKafiGoCsuUjDGwwg-pR1eder08w3n2ulNaSKcOtbY-A2XfT6GQnnGuT_AvInnlLfcwYv42lvjQC2dLQbjx19B0rSzeUUlRYuYc2nh9BXdg43uMtPjngbJvZ6cKsh7DXVgf_96MjKKE72aSKKWYPgdQgdOJESRiuUCsH1-PTEG9lQWG0fhriV1NrTGt_kyZjXsnQ5ZKnPSC9m2ktQ0OIngNuCOLqbjdv6m6FLbDvSgQ-18uZ9Zpo49XEnwAPZbxrDMU0RQnLMA6DvvmTsewFuYeKNE9wmgCWw7o7Gsuo9_jRMH0rLjoWMKjom0JAoVPg1TWLB441tDWGHftkPkkrCCl8yWoVlHbwouIAmldtPFleKraEDFcc3u7BZ_lptBmB10nYUAsHE8R9zD7vIcChsD5Kg6NZond0WIw63vsw2-l2yzra1hHzcWtQo4cXNkxFI3sQKcksERUxPqGbLqiMPHHw1T6HRST03WCXo14IvOa5t5z4Vs2-G6cVpF6UAdL0bSFisq7JGbo4xWyibnSsLw7oCDGa3ZPnfPKuIOaZGAwL_MgghSd8CzyNs4QvbxgfSOTVADbbkT8z5BltngKF-FqJnuN_ouU7T30usMD-v_hKSLdAcDUFPZCXkFUvgAfXm-qaz2U3kIU0bDOA61uVKLL3fdeQiN8xvyq2iz2VZ65THNEKfTDSFtnS6aLngHsiK01WqbfUnv68W7CODQ4Z8_UaQQ87niTgmFmue-DNDTInILn6V1_ti__YJ3aWHPRuwF9eUT19DRcJaSbjWF4gQGuWBr50vMJ2YLBI6XGk0FeopZb_Xo9Di5m_8OQ1zSI6qBtH-6WYSJYSpKTcSo5SuV8cmdxsiIz9-TTsX8bCrqELDBq-2697goKKs8Ym9FF4Z.R8M_0z6gNLzQt0mgA4DdXw";
        String skey = "95B550348A7A11E886DD286ED488C8C4";
        dsrlzSigndEncdSscrtkyAesImpl(srlzd, skey);
        //srlzSignEncSscrtkyAesImpl(payload,skey);
    }
    //Decryption
    public static String dsrlzSigndEncdSscrtkyAesImpl(String srlzd, String skey) {
        System.out.println("Parsing... " + srlzd);
        byte[] sky = skey.getBytes();
        System.out.println("Shared secret Key ID:  " + sky);

        JWT jwt;
        EncryptedJWT encJwt = null;
        try {
            jwt = JWTParser.parse(srlzd);
            if (jwt instanceof EncryptedJWT) {
                encJwt = (EncryptedJWT) jwt;
                System.out.println("JWEObject... isEncrypted=?" + encJwt.getState().equals(JWEObject.State.ENCRYPTED));
            }

            if (encJwt.getState().equals(JWEObject.State.ENCRYPTED)) {
                MACVerifier verifier = new MACVerifier(sky);
                if (!Arrays.equals(sky, verifier.getSecretKey().getEncoded())) {
                    System.out.println("ExtrctdJSon... Not-Verified");
                } else {
                    JWEDecrypter decrypter = new DirectDecrypter(skey.getBytes());
                    encJwt.decrypt(decrypter);
                    JWTClaimsSet jwtClms = encJwt.getJWTClaimsSet();
                    String actJsn = jwtClms.getStringClaim("pyld");
                    System.out.println("De-SerlzdStr: " + actJsn);
                    return actJsn;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return srlzd;
    }


    //Encryption
   /* private static String srlzSignEncSscrtkyAesImpl(String payload, String skey){
        final long HOUR = 3600 * 1000;
        System.out.println("Parsing... " + payload);
        String encryptedToken = null;
        byte[] sky = skey.getBytes();
        System.out.println(" Shared secret Key ID:  " + sky);

        // Prepare JWT with claims set
        Date issueDate = new Date();
        Date expireDate = new Date(issueDate.getTime() + 1 / 10 * HOUR);


        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject("prtnr/tkn")
                .issueTime(issueDate)
                .expirationTime(expireDate)

                .claim("pyld", payload)
                .build();

        try {
            //Log.d("Original Object ", " Original Object: " + payload);

            JWSSigner signer = new MACSigner(sky);
            // create a token based on the SHA-256 hash algorithm
            SignedJWT signedToken = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
            signedToken.sign(signer);

            // create JWE header that directly uses 256-bit symmetric key for block encryption (AES)
            JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM).contentType("JWT").build();
            // create JWE object with the signed token as the payload

            //Log.d(" JWEHeader ", " JWEHeader " + jweHeader.toString());
            Payload pyld = signedToken.getPayload();

            JWEObject jweObject = new JWEObject(jweHeader, pyld);
            // encryption the token and serialize it to get its compact form
            DirectEncrypter d = new DirectEncrypter(sky);
            jweObject.encrypt(d);

            encryptedToken = jweObject.serialize();
            System.out.println("SgnEnc: JWE-Obj-Srlzd: " + encryptedToken);

        } catch (Exception e) {
            //System.out.println(" Error  TAG ", " JOSE error" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return encryptedToken;

    }*/
}
