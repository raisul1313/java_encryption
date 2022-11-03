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
        String srlzd = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0..n5cG3kyNUO9O-Q7o.cI-9YsqFeyg9sdUIqhfHuT19B5cD6yrV03W8OeLCVsI8MtfKUM24GuVPgn0_wy3lWx36PO9jhFoUebLc5kWuTGOvzlP1Kd-JdUvnwhC9OOAeFcPM0DmYNh5nHMSl-buTLHhOEM1diElbbTPU34ZWbnzSssH-zDYVcp3CQEqovVvGzT2GZhS2GOVi_NPabyhK42TArtaKqs1i0Nq7bs7arSkTqVm6luaDLOmMZtGcTThISDuegyFEEVvFymHhBIqL97cSHqjlK08nLhrTK24taDSq08PaTBjsrPpyHBqqyhYxT-0YCU_4d4-CASQ85S-CgzKKbtOyma0HEIk6kxWVwyQVIHiq8MZRpB9diJMTYaAH3gDupUlAdMal2qDo9zSlPM4cx7yc7mGS0bnlKvZThUptZiZz2zBKfs17bWRRYKt8yWZDoETnHypRMD3woO0srlDpyScMAIgSRTmpF1CPqF0al3jkuROfWooLTtb2c4KOZRV3YZIlW2nYKY-TGlWiJWVkVrToNlUKGt5zfnNTjj45UeewccD80EOAKPGjjf_G1RLQG61rV2LVEit9rc8RGfbiKTs3MmSXAbmLyWRFcJ5plEAApjjW-_B-Ba801mh2DCkKv7rZN-CFG58B5DTCtQyTIUpT6YU7i7zFwSm68YhdcbE1jShu29o2amFIPET0yr4xv-QOxhkSFWvM95BEfXfLgsrdO9Ar4-i9pl7ab4mHkxYBdoUHK_OS3aEJQBFczJ8u4KZ_IRb1fHb1DenjGVSzpMVRviRgx-RU8WOs8hcg-70iKy25Tb3baNJEaldzIydM7HTpCsUp44zc2e7Xm1AwWV3Z6OdSeBrPX_5lOrdO9pKUKEbz4Z3Jvu2L2aNMXUC6LCru2ZNv7llldhPasj1QWtJHmnpm9xzE77lURAknzi-zeKdiFrPwlh6GjoBqvGormTd5AfssccawvSdG9NOAoLIDg8P0oZq3qWZtmjkQjaiwuw8c8bF1j-Aw9VmC9iYkgFax0947L9SDELZaBL6nZPi6XY-8eM0KdKmTvN7wYMldBIrZ0yKw2ZfuyQxnabzzh4trZA-xqFicjCDmLsPMyA8twXeuxtEKVXNOrK1lO0plRG53MaaV-8aFFnaEemEPsQNbdEZRAxeDM4R9uZU_3uiM7FhbMHIzRI99enYhqoa8tfUA1HUXsyMtrInENesLCPbpigUOH1H4Abw4AyUvjriR1Ze0CnRcMuK0W2O0I8Xb9CQhh7Jj1u8CtFVzqMWO6aLsZGHzOubwmYKZiwtjYSHzApbDWy19muJEVaT7uOsDy-py9OL_QSf1CihKtrAXMmOJwAF19-XO1Wk0WYhqXA3u_VN4EX4S--lpNJ8mAPyiNlZoF6Nru2wD4Yz-9KChGCGI1iyx7a9BTXYTYP7Of2QH3n2INqZMrlr9V-btLCQ8LlR_XWOTVpUSaGeVAuixEXVrjFqnc6O2aYcwgpS43LVi33Za4SwiHll9WqDENGeRhjBOFZBWxfgOd7IutAOYqkaSfmgHHCqNxSFCx-qX8PewtTJI3gcP_J384BbNf4FlzntuHfNsZQJ0mRacHorZxTDx88oXVm8WB6ge9v8jqVHU_5SCwq86mMGi6K1-Bi9MsDLEMXQzZ8-rsOhmEyT3nVlZyQtP8gLQdOJxYkm9R7gW9P4OkFm0CqQ3hhrSZzhbH491BpU65g71zfGgCtm9lXilmLlvpP9iXKxRNj5T2amL1vwSV83ctdeRrbkWa66vpAv2SUOLCgocwWHdWl620izPJwOZVnsfVMGDwQ-ev_NELE8Ymwlgg8_Jhaobhty3LdaJoJnO_cooq23MsoQQc-wc2Gta4Gbaa5dcdWra2_GKNGN3WUbh4zOYb5aJHyoZiNEjGKd2WaLwZWJonAPHpY6IWz5n1l5uQ1Z7aFc-Cmsqxl3UUrkKSEomO1DA0n6MRQeuxzzM2Yo-EHwpzW325dV9u_FrSAQlFtXv3HYqh36Mr8srl8ibjRt4TEDqBvG4wVVThDi58qQBNo1Js2exQtTLBHNuiCFKdTyQmOGdn6x_KMHEgg27U9tZ5I2C_27cuQIsi0dwAcKj6OgtfAv_xCqQUJFx_rjTKvIDpD_b-nS8EKgBkVdjsfhDpF3J2BBQBezBlNqOiP2x4Qv1giQIL5SJaCOb6JayyCbH7fLaJtOd-1vmMozsxddX1m_406zhsa_gfM5YGoVzHjpxD_U2_Ka33V3yYIDeWN_VqTCqyzED4H82xUx3hYzWnmu1wT3VfW5EVqzUdBauAfKzxlrAh2Ov2_xP2jvCeGZH5UJePTPBn3fohJ2T9xxK4X0GiYEz5lbouGaEQHPOCUoyyI_ZihuhXc_xKYul1_EhqtUqV8LQzstBcD7irCIAG71r3Aqr2vkU06pqzrJbrDefARMKSx1j0lDMUnZhaIES9XUgK71i7BkYtAdjE-RjMZ7wlQYVUDGJyZEy786bLj_5YWHSJF0knhSKeJFPp_RiKlaj-tBsAek8OXoiuJeKM992AYZzrBbbj9uQQRRWQ6ry3lZYq18HB6Pny97zKB0Z5jVHbBVoopDVmIj6DKN_910eh-KQilvzc5iauddne0MKNvvpCNzz8U4WI6tfR7ib6c0lELTPQ9sMoWypOIq97vjI4chP2h09Pp8YmXQfLSuIqCaGV46EIjm0UAzS9uXJ2HRn6gkN8vlkrWPa3zwXHrndjvsMTd6N7sPx21P2LIuJCSsxm3F_AGPMJ4bX59bj-Pt9H8D59G1mDgKi-MwUkbBapgVyOf7aFZJPRlp2HieTyl2ww2Ck0hlSlR4rGUtQpcswa4EwJwTmincT2q6j3bmufpzQOLlVRris1jsTgnMReqHDrezP7HrpAaFjPnOR7vbwzr9AIS3Y82huamv1rxVMUCTPci9JwgFDigatoG628vVTiM7qQTqXHb9QUYKWWYjFAl66Z8jZvtDKc1nLd8_RkNBKKgzfoEIVRjEPNa3hGrF85UCpcYVu8PuIANSF0IU2Epb44lbmdqUh4Jm0DToERmaf993mQvq_wNnfvgokbdD5Oy8kM9wlhfWA6lpmDKTyS1W93cweY90LMXLJ_9j_bKxgcZ407Aolifc1LpB0PY6gB__Y23QpTy3aJZ_ofgToVxF7cTtRSE59BXRP_HAbdq-OCR8NYWaT4pX3p8r9cbZosG-tkCKT32K4836Nhy9uH9qriVgrWYe0Cej0hmov5qIc6H9s3iSa3xFEoxWwSDk0gmpODUa-AGbWTHzbwz0jJspBu2ajKkx18ayRZBIqbPHCQAL0To6okaPc1YmdRNgp3dmuCoKqxVjiuSTWWC_eDB8sVPJRX7UA-QPVrkac_nuqbUUhJriZCw64yjpJW4VFUewhSwT5BRjRSNgMgEKMLrDRF1v8DwsGPwVGcyXReQUoG1a8RaffbsU-COU8KYkZ_eS2ESNdIMpJYj5B9WQ9NemPfKjVJdo3jm02QT9fS5WK3gpJcPgRXoP-HinPYms_Cf5GTSvbuk45dSZbwRcs7zJzBWXUSiSCP0zvGDj_1ynxqbXf5SBBpePOG-r5W4z0jsHlpkJZl09LYrOQbZb_qzwUOU2f_x2-Y_g0QfZzKYkloi8ilAK2LV1fBJ-Dk4gt9tTYz7e-6TejEg__sHh5C5qs6IqMUDYb2Ja3ea8E8VaIJ7CFcWqoDgW2kfJB8MljJnz9_MUVL8Azv6smR_FMbJzm8O0IQ21o3YBl-pn457-shTgYZ8wpycwDuzqensP0QkpHPW_HEaDpR-A5-rXkbdXQ9nNmq6qxHkGNI7iLFLQhmj_ceJV9tHeKiVqYG9P8bMoUswmgpf1DVAUr-T558BUMKcZ83VwNFdCqvjOw4gl5JykPsiUKzWgK8-UKdGYIscYfX78Qg6fYMJITYADRnKMJ-jNRh2Rmv0D3P5LQMDROUEmW8n5b-wohBeNBX_nIFe-cUOG5EPkf17P3hBp15wmYx4ZgYxKvFJOYhXlyvHjxuRcajFBxbyTps6PXFqbXI8LC5RomoGL8FC-8x0UwlrJ7OSISSfhOYzTOTHa53YXJEjoDMaT2dIKbpBuuZgldZ4w_ahSwLiVSQ7hrjsQ1N-6Q2L6FkhmDI2NCxFHIZhwI81GJyR8a-KyhVKIeBijiv4nqeuZBt8a8iPKBW-Qx1G2E00emBQffsj8WOjbZqMFWtGNtPD4P0YLyAYf7KcEafFaoJqlcrEJw6VWhh47dMq8yNpJsqygwdFVTMDePOcf9sE098fkMeS_qXVcEY6Tk0AKvku8KEmLAX6kzieesKD5R--1mPgcWxUph7C7PYQ347bv8VlYY8q8MkNB42yGRik98Z_fGtFb1_z4VOUY9Kdf7SUl6maIiG5UH7zYHGWYfP55q_xtVy09GdEDobNNN-Kpeyr0gaGbYtm4vZX083c7TwNHDy_JD-MjeDUDB5SUM4hvmT523_IphHw3-D1IXE4vgiEi7len96O0MH70tQPuItJti2a0Ork3ApofqKarv0K4dSrfWwihBe3wfX266MFPpvRn85WRAQVn8EBeCEHh6J1SMxcH6hqzvH43_M9X3HGVqWv1QI8rEK3maFlJN7RoXCou7SLQFSMQcHfY8t-HQNcKNlZbD_6Bhoo1JRANQI3DP5IlMqQHcuUFyoxeVr9GIur_y1jWewhQYv3-jhHbDZ0Z4I_Z9IuhImyR_DDvYVkvXpO3wAGGmH6-ktt52coQQkApceRz3-bN_P-QB2IES59whuvPvoMwEbVMnmTzcnXdaGCKIX63lp_BVhENGNOjoJpVXKSHfr3MNiAjRX4fgHOxwVzxnIDBlTbzVkTpmto9qEKPIk3XwYyd_av7QP0A8m1SR2OnJzz51jKsaDSsBI4yp207slOTjbEAzxUeWywexPzRABxeEMibF0tPbhVH9ktG6QwXdBVETRMX4-RL2e0jtMgrxJ9g2_coLVi-kfbN61iebk9Oj-kPa0lR7K2yvHsllLk0nOtzYvfxP0e1zKOhG7N7YW0qUidKelYBLuxLD3hGF-k-7vLhnt1Cmsx0pLp3eYIY5KGxZNZH1m_h8UbjwZYU-DPVokz6gNxLr7A4ft7-vSDuWw0J9JmyWBByC7OJCvyZJzyYS1XX_latN3z9oXVrunNcnc_WRd62hq2R8Tvnx4w5ydoqVR2Mjl0ZzfKfMXzaoX0rmTf34fME9XJE10MbaMfGZoihiIjFHa-9ZVoFBo-T2ew284TzxOCg_EOriHz3Xj_U7-Oh7u38FDZiUVPwDlJjFUUoeGskAoeaXZ9fl2RCM4xm1kVWHSf82uRdti2Sg2xoh6sljSgrf2Q4XnB1GZaju0FQXQw41-lN4Nm1yB0eU1oWr5yZMKFP5Pc-9eEEtr6HIDE8AkmOSbMgIaakvB3IY3-BFN0dSN_5QMmZzeHCOG8nKbfg6lUnFXGe6mJrb0FjgV5Bc2tP9wb5FsSBfYy-JZlADaQGCYPLitDZ6YE_km8i_eZxja7VsDlfT2sJ3vKFg5xpeI-OATILQcC7MuVi7gUrGKwp7YAX661mH2iy6ClEWen8eOdyCw7mgBrbnAjKgD4NxCM2EDRSlZ-McJn2y-ZgE1-WwEOMjqm0HFZvRbnFiXOnxZTxLc0G69fdYSEY2OQF-fLr32TIPSTamQ0c2YTSefFygG74VXoaIPP7iEybtDJSWJ5JsL3YdlateunzGDrr7ilXvAtHEKrm0ROViWWOTnRAnhtqBEyI_UMQ5xJeyRYEBGyLOcuPDfvKNqwo1ymbXgHe0cem_42GJ7YsL2a-UBgnxqOdqu90tYlMAxKkaRi0AWI4d7hVs2PooC9dNL9Zlit8YWIGDLamuE98bIWL0eGvVcKjS0UoB-J5nyJLqcDzNVqTaO4qvG7XS3XucIzDfyGkRg3FhXUGeYPleH8yarXJhGsYuPCx_hwwY2PyxT9HfKZ7-MDivvIhaeh8MDjCcNtIp4Re_UrCTRL-Grmo30wiFhKxRWiurYNlrtWYNUsNNecDYfdW_XZLbLjjD-xpxWnSsxXi6ArHd3_mKAl7kCc3PGlea4KqQF9Sa5k8WlHyCCgBIixlSFErC1y6Ts_bvubOT7kGvcJFvxaYjE3cRvaCacPhCmkrnSJQBMD03xIjTf8t1Og_7PMTIiHHr3tcVAMkR52BkW_9QILMd2InMv7sYncU2ZoQON-jP5kABhV7UJhcZzey9dv11fj3mWtetvMCCe6I3spz2qQXDnsRRhDbX8ytU7-zZ91oX9IZJCAx8dhvvvzGp5z3dhwez43Xkg10P2vBj_U0lGDJmkj9XTAekC59Obre1hb_q5VGJZvJ1NkQPy6Pb0-i1FgG-ryRclFNdYN9DTntNY6zrp7HkzNKhNGLEmHXfBbeY8jkexBT_ZWeIsK8Dd2i_ZhrDHAucYNU4GlKxMuuiKo4TjSTT3R5PHspp-Dm9DHEn5FRXpZSzmRCO5cnBp0Qa2o7eHmPZwOB-vWIuI3HvLCVN8qikUCDjok9FkZ-4N2Utvh87Bkj8t7_EbEHMUPUf02bxH4rytN9OzlGqzMmB2L9bcjZxlpTIPINlxgFU8oFvdkpsfr329hROsDNaKLLPvBGQ82zKbf9hKzXlYmYFXwGAjgz4H7qdJfDBolXVl81NJ2VB-0GzL656BOWq9Q-dGW3Bt7yrPQNqTlAslL7u-bdxU1IXGX7I4riKpjQ5oQ4uPjx6N7WaY8T56rgG0AfUDPtllby7LNcUr96YmgAYdGNZoCHiEUADtSEWjmvkWGyibZfdx1vdVaW_UozwyQoVclCF0no4wwmQG-ALjg7mwgNjsfDkn497ZwJyrkv7eicBAXNaXoPAEVdHFjJeIUP563fQldrJunIJl8a0IORLBauWtxCgECcm8oDKgD-sVlc0W5FLiOmuZmzufyQSPyBODGJw-Biu0st9XQRS4bM_1VrSWKOCL1mjp2KmVzKdSYY5uq14dVplvpS3tB2KgCB4mn3rTCvMc0bbDNFpiVDl5RE_C3PrNdcKRTJRYD_ykUYjbYHmKnnjFGVI0qGa7AeYh8rt7O9nb0LL-coq377-bj5J6JP1SGlh48YJJCpVkeF-TpntWFS4-SKBWrP6ZWgC_qxZ--VtF5R5UdW9UgU-Gf_tvXLNWWxi1O685u310XuyNYtVzTM_X3827POVu_HCiDvyhIN5164nBsiudyTn_4xQUxDZf5mYXmbdS2bCbnDrZgOXIvTB_ZFf89kDS5G4qoSe-GpKFHR9PbK5yJC-uYxHdqMRHGC1bafJKu84n5smGqCgW38-TJsLpy7yJE48eY3IR0_WX5ozOBrVr0Zg_1EdtxCTlQxryaSiAgezh6qSqnGcTsMrCIIT3VHHn_g4kxkP3Pr_kwZOZ5VYw8t0YOfopWn5Wqv8TZiLBde1cnHHdUiH1KNIL56x6zewUVsW3-xJStTegSeqr-ckyjCEGvYre6bMrimklMRXMZpZo7zFJaMD_PwOBumYVSx3ni4x4SXEXO6W6_A1J9MHfaClJVg2bLuWUUhSKLNdCAFBJiFHK_D8WtahmLqSfNTOiPolM0UaHY2Fur3wUJ2P9PxEymXkbbW3KHDcUas3SyizXMkwqyC-eHnv3JTVE4RZ8ecXpNniTcoN2UW4zsqGiSLajdyNPpXjJNLbYluFVIZ92xPWHQDVz5JoSwQYLbKGfpXW4V3BdKcYrRobTS1JKlL2Uvs5A4QXnwDaJiG0sVSPdpgt9uLP0EJBd4tX6xjWPQsDCsT8H6KStKcJefbSJCIu0ctNAoJg1J4dz4852TlMA7lvPTxGKfcyaIk7utoFbFHs1KkiDjiPlg_z5HZrHZxU45_Y79cb7HtkLf4zC6sg7qxAPqoBR_DIhVCLN3zIuXdCm5TJg_8iMNaKtJKGi28dV1iRg4_UUAnKYVmsreES9pW6UMe67Gu16eEb5oTQnu46XjcnfHJot7YgwQ9lWG4U3WLgmT-KVdKs_Ss2yaD3_aEWonOOZEKjZXPW2jgO5XaaVqR_GY6BUnWX3tQpDC4ImbWQqs8bGlbpDEi1TVTO-eKJ7Fk2bNcZEy1exjNDns0X8_uxJOOislOmDQ6UoZ69tr8dXUqF2Dzk-AaXB9_LIgRh-luN_WOs8TiDVv7oHyWZQ7v-qYO9fneJ7s-6WYR3a1vfqlrAH0prkLrW6g0F6F7KWpiZyLza-Fb6nBOFr9QaycUUY62dFALln9jboduGLeMUONUHNsUVk-OH-8CCYnP_ByNdRWJwBWUF3oZnW03MkrzFQgPdwMd-eQDZyKX7r_tM8wbgayMGthr3rGDKXyqW1UJzKoD1j0RxSFJZ3u9d_x0RpThiOx7aGDPIqtbl43-pIs2_eBoiiH8nKHVLcCCm_u1HlWVwmwin1T6n_WAtfmRK6Ji1173vhV55-K3oXR-gXTrPioQHrY4ZdigOsv65QgLK6xpKtZIMiD5cpCv9mcAqivaTzbQk8TMZUEizn1flNY2IGL9PgT2TIRF_zD_8a5xuEztyDVkGUcprbZQD1Uiq8hS3YroDeGa5eMjfzT4V2oVSXDNLFUQAbScqPzmZtoNdLoShM4pMYvA3qtHGhhu1LDzxa9ebczPI6VbHTZtwsCZXCaNgYSB1Ux77BrT49WEkJX6W8GlxnKCrCidLhVK8rkqgT6RQg8hPJby49bBmof7BecYNOftG4d5xbIkwj_r6rp8okh0ISKgaE3tgQ5X0MqAZs0P2_xJb1S552TLHrwbg2ed0nEqN2e_2sdP8jbuyS4BAMUiFtCiSkEOl2y7JUq8h5mPpv3lUGyF_2ZrD2ZjlBxzW7Zda_omZSD0lWGpGpI_JQK23sQGCvtcDs8UCgVWj9zsKoyMFy3y3GS-YJ3aJrbOyVcWlQR4sY26t0v23MJII06GmRCU1m4wFZUCkVwZ8qg-kjapfScil9dN8oVy9avhBybxGqt3HcG04EdCsb9pCitJe5d_c8szNrnB35RQ9CNyF9_EyYosaqkrSdq4ulwnlH1g_dEQ2rMQ-C4I29X35sBrrMEcodN72J2MUfjWzbgfI4OAu3KyRe5O6sgb_4nW6dibmELVBsTjNETA5wryb1xQ8xjHhgzS8PrdJSpqT2NqvKbPfV21xabwZuUDzMQk0Y-a_01V1WN05JfdJB_caerGf3SeJsZYd7VXhg0IWUZQWC99ABBMxNncRUMMGa9y9Kc292zwIAR5-lmZ8srvYI1z0nxSxtM6JSki2DKDxs0BkyILsTAg5ZJRHUk685YvGz7dS-h7nra8tP9ALrkF8wizzZFcziJHZ9mlJva10pLp51GrCduT9I3Ew6CjRklPalTtwPSU9D34yczPXhrmDxxmiy6r9BBFNfwpAOCCmAmn2uzIG4aWPLwn84qw34vbYuYxp2R0IyvuSNXUK2978YPnxJyjgQGqJzY9Kzql-1L848TitjscXzqiggod4fIWoWk1mqUs_rqjpqTn-dj6fHDVeJybmOa1-3q2NteGBEYHGWrMD_puHHwMO0q0cRsvJzG34-SS5jIjg.seB1zvJyV7K8V9-hRzEhNw";
        String skey = "95B550348A7A11E886DD286ED488C8C4";
        dsrlzSigndEncdSscrtkyAesImpl(srlzd, skey);
        srlzSignEncSscrtkyAesImpl(payload,skey);
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
    private static String srlzSignEncSscrtkyAesImpl(String payload, String skey){
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

    }
}
