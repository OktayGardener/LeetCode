def match(word):
    P = {}
    for x, y in zip('abb', word):
        if P.setdefault(x, y) != y:
            return False

    return len(set(P.values())) == len(P.values())


print(list(filter(match, ['ekk', 'aba', 'lol', 'hej', 'omgg totallly'])))
