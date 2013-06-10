class Node
   BREAK = '>';
   EOW = '$';
   ROOT = ' ';
   attr_accessor :letter
   attr_accessor :children
 
   def initialize(letter=nil) 
      self.letter = letter 
   end 

   def [](index)
      children[index]
   end

   def keys
      children.keys
   end

   def include? key
      children[k]
   end

   def addChild(letter, node=nil)
      self.children = {} if self.children.nil?
      if (!children.include?(letter))
         node ||= letter != EOW ? Node.new(letter) : nil
         children[letter] = node
         return node
      end
      return children[letter]
   end

   def to_s 
      self.letter 
   end
end



class Gaddag 
   attr_accessor :root_node

   def initialize 
      self.root_node = Node.new( { letter = Node.ROOT };
   end

   def add(word)
      word = word.downcase
      prevNode = []
      for (var i = 1; i  j)
            {
               currentNode.AddChild(c, prevNode[j]);
               break;
            }

            currentNode = currentNode.AddChild(c);

            if (prevNode.Count == j)
               prevNode.Add(currentNode);

            if (c == Node.BREAK)
               breakFound = true;
            j++;
         }
      }
   }

   private static string _GetWord(string str)
   {
      var word = "";

      for (var i = str.IndexOf(Node.BREAK) - 1; i >= 0; i--)
         word += str[i];

      for (var i = str.IndexOf(Node.BREAK) + 1; i < str.Length; i++)
         word += str[i];

      return word;
   }

   public List ContainsHookWithRack(string hook, string rack)
   {
      hook = hook.ToLower();
      hook = _StringReverse(hook);

      var set = new HashSet();

      _ContainsHookWithRackRecursive(ROOTNode, set, "", rack, hook);
      return set.ToList();
   }

   private static void _ContainsHookWithRackRecursive(Node node, ISet rtn, string letters, string rack, string hook)
   {
      // Null nodes represent the EOW, so return the word.
      if (node == null)
      {
         var w = _GetWord(letters);
         if (!rtn.Contains(w)) rtn.Add(w);
         return;
      }

      // If the hook still contains letters, process those first.
      if (!String.IsNullOrWhiteSpace(hook))
      {
         letters += node.letter != Node.ROOT ? node.letter.ToString() : "";

         if (node.ContainsKey(hook[0]))
         {
            var h = hook.Remove(0, 1); //Pop the letter off the hook
            _ContainsHookWithRackRecursive(node[hook[0]], rtn, letters, rack, h);
         }
      }
      else
      {
         letters += node.letter != Node.ROOT ? node.letter.ToString() : "";

         foreach (var key in node.Keys.Cast().Where(k => rack.Contains(k) || k == Node.EOW || k == Node.BREAK))
         {
            var r = (key != Node.EOW &amp;&amp; key != Node.BREAK) ? rack.Remove(rack.IndexOf(key), 1) : rack; //Pull the letter from the rack
            _ContainsHookWithRackRecursive(node[key], rtn, letters, r, hook);
         }
      }
   }

   private static string _StringReverse(string str)
   {
      var charArray = str.ToCharArray();
      Array.Reverse(charArray);
      return (new String(charArray));
   }
}

