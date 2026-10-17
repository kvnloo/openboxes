<!-- oss-factory:v1
{"id":"20261017-civic-openboxes-queue","kind":"state","project":"openboxes","from":"chatgpt","to":["factory"],"status":"working","dedupe_key":"promotion-queue:openboxes/openboxes","civ":{"city_id":"oss:openboxes"}}
-->

# Canonical upstream promotion queue — openboxes/openboxes

Tracked via registry PR https://github.com/kvnloo/oss-factory/pull/191 (provisional until merged). Fork Issues are disabled; this fork-local document is the **one canonical queue**. Original implementation PRs, issue threads, and author credit stay where they originated.

- Origin: https://github.com/openboxes/openboxes
- Coordination fork: https://github.com/kvnloo/openboxes
- Last evidence audit: 2026-10-17
- State: **HOLD / OWNER_COVERED / CHECK** (not origin-action authorization)

## Existing upstream review / source work

- Original issue: https://github.com/openboxes/openboxes/issues/6201 (maintainer jmiranda has reproduced the memory problem and is actively fixing it).
- Maintainer origin PRs: https://github.com/openboxes/openboxes/pull/6263 (quantity-on-hand DB aggregation) and https://github.com/openboxes/openboxes/pull/6264 (transaction-report DB aggregation).
- Downstream fork PRs https://github.com/kvnloo/openboxes/pull/1, /pull/2, /pull/3, /pull/4 are all **closed, unmerged**; original branches remain evidence only.
- Avoid promoting overlapping #6201 retry prevention or aggregation paths while maintainers own them. Mark matching work `OWNER_COVERED` unless tests find a narrow independent regression.
- Separate #6190 demand-table atomic refresh remains `CHECK`: MySQL/MariaDB DDL + integration tests pending.
- Next: review #6263/#6264 for unique correctness findings, run focused MariaDB/MySQL tests, and avoid duplicate PRs.

## Release gates

- Record original author/reporter/maintainer provenance and exact source head SHA.
- Follow project contribution policy, current upstream reviews, conflicts, and CI checks.
- Require native targeted tests; separate static reasoning from exercised runtime evidence.
- Do not publish an overlapping origin PR or claim a fork draft is landed.
- Only a bounded human authorization under factory/HITL.md permits additional origin actions; upstream merges are for maintainers.

## Results

- **Ready for new origin publication:** none independently verified.
- **Origin writes from this coordination update:** none.
- **Next update:** when CI/reviews/test results or upstream ownership changes materially.
