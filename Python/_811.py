import re
class Solution:
    def subdomainVisits(self, cpdomains: List[str]) -> List[str]:
        return self.subdomainVisitsHashMap(cpdomains)

    def subdomainVisitsBig(self, cpdomains: List[str]) -> List[str]:
        alldomains = []
        for entry in cpdomains:
            count, domain = entry.split(' ')
            subdomains = domain.split('.')
            sdomains = []
            sdomains.append(domain)
            for i, x in enumerate(subdomains):
                sdomains.append('.'.join(subdomains[len(subdomains) - i:len(subdomains)]))
            sdomains.remove('')
            alldomains.extend([(int(count), dom) for dom in sdomains])

        from collections import defaultdict
        d = defaultdict(int)
        for k, v in alldomains:
            d[v] += k

        return [str(v) + ' ' + k for k, v in d.items()]

    def subdomainVisitsHashMap(self, cpdomains: List[str]) -> List[str]:
        ans = collections.Counter()

        for entry in cpdomains:
            count, domain = entry.split()
            count = int(count)
            frags = domain.split('.')
            for i in range(len(frags)):
                ans['.'.join(frags[i:])] += count

        return [f'{ct} {dom}' for dom, ct in ans.items()]
