package com.example.ygodeckbuilder.utils

enum class CardType(val typeName: String) {
    SKILL_CARD("Skill Card"),
    SPELL_CARD("Spell Card"),
    TRAP_CARD("Trap Card"),
    NORMAL_MONSTER("Normal Monster"),
    NORMAL_TUNER_MONSTER("Normal Tuner Monster"),
    EFFECT_MONSTER("Effect Monster"),
    TUNER_MONSTER("Tuner Monster"),
    FLIP_MONSTER("Flip Monster"),
    FLIP_EFFECT_MONSTER("Flip Effect Monster"),
    FLIP_TUNER_EFFECT_MONSTER("Flip Tuner Effect Monster"),
    SPIRIT_MONSTER("Spirit Monster"),
    UNION_EFFECT_MONSTER("Union Effect Monster"),
    GEMINI_MONSTER("Gemini Monster"),
    PENDULUM_EFFECT_MONSTER("Pendulum Effect Monster"),
    PENDULUM_NORMAL_MONSTER("Pendulum Normal Monster"),
    PENDULUM_TUNER_EFFECT_MONSTER("Pendulum Tuner Effect Monster"),
    RITUAL_MONSTER("Ritual Monster"),
    RITUAL_EFFECT_MONSTER("Ritual Effect Monster"),
    TOON_MONSTER("Toon Monster"),
    FUSION_MONSTER("Fusion Monster"),
    SYNCHRO_MONSTER("Synchro Monster"),
    SYNCHRO_TUNER_MONSTER("Synchro Tuner Monster"),
    SYNCHRO_PENDULUM_EFFECT_MONSTER("Synchro Pendulum Effect Monster"),
    XYZ_MONSTER("XYZ Monster"),
    XYZ_PENDULUM_MONSTER("XYZ Pendulum Monster"),
    LINK_MONSTER("Link Monster"),
    PENDULUM_FLIP_EFFECT_MONSTER("Pendulum Flip Effect Monster"),
    PENDULUM_EFFECT_FUSION_MONSTER("Pendulum Effect Fusion Monster"),
    TOKEN("Token")
}