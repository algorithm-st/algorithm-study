
SELECT CAR_ID, IF (CAR_ID IN (
    SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE '2022-10-16' BETWEEN START_DATE AND END_DATE), '대여중', '대여 가능')AS AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY CAR_ID
ORDER BY CAR_ID DESC

# SELECT CAR_ID,
# CASE
#     WHEN '2022-10-16' BETWEEN START_DATE AND END_DATE THEN '대여중'
#     ELSE '대여가능'
# END AVAILABILITY
# FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# GROUP BY CAR_ID
# ORDER BY CAR_ID DESC

# SELECT * FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY ORDER BY CAR_ID
