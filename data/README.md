# JavaGOAT data

| File | Role |
|---|---|
| `profiles.json` | The agency: read at start-up, written when the application is closed. |
| `showcase-profiles.json` | 12 hand-written profiles that demonstrate the matching algorithm (see their `note`). |
| `matching.properties` | *Optional.* Overrides some matching settings (see below). Absent by default. |

`profiles.json` = the showcase profiles (ids 1–12) followed by 488 generated profiles. It is rewritten
by the application on exit, so `git status` shows it as modified after using the app;
`git checkout -- data/profiles.json` restores the shipped version.

## Regenerating the dataset

From the project root (JAVA_HOME set, see the main README):

```powershell
.\mvnw.cmd compile exec:java                                    # 500 profiles, seed 2026, today as reference date
.\mvnw.cmd compile exec:java "-Dexec.args=--count=300 --seed=7 --reference-date=2026-11-10"
```

Options: `--count` (total, showcase included), `--seed`, `--reference-date` (the past matches are dated
in the 45 days before it), `--showcase`, `--output`. The same options always produce the same file.

What the generator does (`DatasetGenerator`):

- heights by sex (women ≈ 165 cm, men ≈ 178 cm), IQ ~ N(100, 15), plausible lifestyle frequencies
  (62 % non-smokers, 6 % vegans…), body build correlated with sport;
- passions drawn from one or two "personas" (gamer, sporty, artsy, intellectual, homebody, traveler)
  so that people share interests; everyone has at least one video game (the forms require one);
- preferences derived from the person: 88 % heterosexual, 8 % homosexual, 4 % both; an age range
  around their own age; non-smokers often avoid smokers; vegans/vegetarians prefer close diets;
  some believers want the same religion; physical details are mostly indifferent;
- about 20 % of the people get 1–3 past matches with compatible people, with votes for 60 % of them.

## Writing profiles by hand

`profiles.json` and `showcase-profiles.json` share the same format. The application refuses a file
with problems and lists them all (profile, field, accepted values), so mistakes are easy to fix.

```json
{
  "version": 2,
  "profiles": [
    {
      "id": 1,
      "firstname": "Camille", "lastname": "Moreau",
      "sex": "FEMALE", "age": 29, "ethnicity": "WHITE", "iq": 118,
      "size": 168, "hairColor": "BRUNETTE", "hairType": "WAVY", "hairLength": "LONG",
      "smoker": "NEVER", "alcohol": "OCCASIONNALY", "athlete": "SPORTY", "feed": "VEGETARIAN",
      "bodyBuild": "SKINNY", "religion": "ATHEIST",
      "videoGames": ["MINECRAFT"],
      "passions": ["YOGA", "TRAVELING"],
      "picture": "file:/C:/photos/camille.png",
      "preferences": {
        "sexes": ["MALE"],
        "age": {"min": 27, "max": 35, "importance": "REQUIRED"},
        "smoker": {"accepted": ["NEVER"], "importance": "IMPORTANT"}
      },
      "matches": [{"with": 2, "date": "2026-11-03T15:30", "vote": "POSITIVE"}]
    }
  ]
}
```

- Required: every field of the example except `videoGames`, `passions`, `picture`, `matches`;
  `preferences.sexes` must contain at least one value. Ids must be unique and positive.
- Ranges checked: age 18–120, iq 40–200, size 100–250 (cm).
- Values (exact spelling, the typos are historical):
  - `sex`: MALE, FEMALE — `ethnicity`: WHITE, BLACK, ASIAN, LATINO
  - `hairColor`: BLONDE, RED, BRUNETTE, BLACK — `hairType`: STRAIGHT, WAVY, CURLY — `hairLength`: LONG, HALF_LONG, SHORT
  - `smoker`, `alcohol`: NEVER, OCCASIONNALY, REGULARLY, DAILY — `athlete`: SPORTY, SEDENTARY, LAZY
  - `feed`: VEGAN, VEGETARIAN, OMNIVORE, FLEXIVORE — `bodyBuild`: SKINNY, MEDIUM, OVERWEIGHT
  - `religion`: MUSLIM, CHRISTIAN, JEWISH, BUDDHIST, HINDUIST, OTHER, ATHEIST
  - `videoGames`, `passions`: the constants of `Passion.video_games` / `Passion.miscellaneous`
- `picture`: optional URL of an image; absent or unreadable → default picture.
- Preferences: any criterion can be omitted (= indifferent).
  - ranges `age`, `size`, `iq`: `{"min": …, "max": …, "importance": …}`
  - choices `hairColor`, `hairType`, `hairLength`, `ethnicity`, `bodyBuild`, `smoker`, `alcohol`,
    `athlete`, `feed`, `religion`: `{"accepted": [...], "importance": …}`
  - `importance`: INDIFFERENT, NICE_TO_HAVE, IMPORTANT, REQUIRED (eliminatory)
- `matches`: one side is enough, the other side is added when loading. `date`: `2026-11-03` or
  `2026-11-03T15:30`. `vote` (optional): POSITIVE or NEGATIVE, the opinion of this profile about the
  other one. The feedback percentage of a profile is computed from the votes it received.
- Unknown fields (such as `note`) are ignored.

## Matching settings

The weights and tolerances are documented in
`src/main/resources/com/example/javagoat/Model/matching/matching.properties`. To change some of them
without recompiling, create `data/matching.properties` with only the keys to change, e.g.:

```properties
# common passions count more
weight.passions=8
```

An unknown key or an invalid value stops the application with a message naming the key.
