WITH tariff_rank AS (
    SELECT
        r.account_id,
        t.name AS tariff_name,
        COUNT(*) AS tariff_count,
        ROW_NUMBER() OVER (
            PARTITION BY r.account_id
            ORDER BY COUNT(*) DESC, t.name ASC
        ) AS rn
    FROM readings r
    JOIN tarrifs t ON r.tariff_id = t.id
    GROUP BY r.account_id, t.name
),
user_stats AS (
    SELECT
        a.id,
        a.username,
        a.email,
        SUM(r.amount) AS total_consumption,
        ROUND(SUM(r.amount * t.cost) / COUNT(*), 2) AS average_cost_per_reading
    FROM accounts a
    JOIN readings r ON a.id = r.account_id
    JOIN tarrifs t ON r.tariff_id = t.id
    GROUP BY a.id, a.username, a.email
)
SELECT
    u.username,
    u.email,
    tr.tariff_name AS most_frequent_tariff,
    u.total_consumption,
    u.average_cost_per_reading
FROM user_stats u
JOIN tariff_rank tr
    ON u.id = tr.account_id
   AND tr.rn = 1
ORDER BY u.username ASC;
