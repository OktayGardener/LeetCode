class Solution(object):
    def groupAnagrams(self, strs):
        groupAnagrams(strs)

    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        r = []
        d = {}
        for s in strs:
            if str(sorted(s)) not in d:
                d[str(sorted(s))] = [s]
            else:
                d[str(sorted(s))].append(s)

        return d.values()

    def groupAnagrams2(self, strs):
        ans = collections.defaultdict(list)
        for s in strs:
            ans[tuple(sorted(s))].append(s)
        return ans.values()
